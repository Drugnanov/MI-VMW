package cz.cvut.fit.vmw.slamasimon.flickr.service;

import com.flickr4java.flickr.photos.comments.Comment;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SentimentData;
import cz.cvut.fit.vmw.slamasimon.flickr.model.CommentedPhoto;
import gate.*;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon.
 */
public class GateService
{
  private static boolean isInitialized = false;
  private String homePath;
  private String pluginsPath;
  private String sentimentGrammarPath;
  private SerialAnalyserController pipeline;

  public GateService(String homePath, String pluginsPath, String sentimentGrammarPath)
  {
    this.homePath = homePath;
    this.pluginsPath = pluginsPath;
    this.sentimentGrammarPath = sentimentGrammarPath;
  }

  private void init() throws GateException
  {
    if (!isInitialized) {
      File gateHomeFile = new File(homePath);
      Gate.setGateHome(gateHomeFile);

      File pluginsHome = new File(pluginsPath);
      Gate.setPluginsHome(pluginsHome);

      Gate.init();

      // loading ANNIE plugin (DocumentReset, English Tokeniser, Sentence Splitter, Gazetteer, POS Tagger, etc.)
      CreoleRegister register = Gate.getCreoleRegister();
      URL annieHome = null;
      try {
        annieHome = new File(pluginsHome, "ANNIE").toURL();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      register.registerDirectories(annieHome);

      pipeline = createPipeline();

      isInitialized = true;
    }
  }

  private SerialAnalyserController createPipeline() throws GateException
  {
//    init();
    // (ProcessingResource) Factory.createResource("module-class-name");
    // below is a tip, how to find module class name
    // create an instance of a Document Reset processing resource
    ProcessingResource documentResetPR = (ProcessingResource) Factory.createResource("gate.creole.annotdelete.AnnotationDeletePR");
    // create an instance of a English Tokeniser processing resource
    ProcessingResource tokenizerPR = (ProcessingResource) Factory.createResource("gate.creole.tokeniser.DefaultTokeniser");
    // create an instance of a Sentence Splitter processing resource
//      ProcessingResource sentenceSplitterPR = (ProcessingResource) Factory.createResource("gate.creole.splitter.SentenceSplitter");

    FeatureMap gazetteerFeatureMap = Factory.newFeatureMap();
    gazetteerFeatureMap.put("caseSensitive", false);

    ProcessingResource gazetteer = (ProcessingResource) Factory.createResource("gate.creole.gazetteer.DefaultGazetteer", gazetteerFeatureMap);

    // locate the JAPE gramar
    File japeOrigFile = new File(sentimentGrammarPath);
    java.net.URI japeURI = japeOrigFile.toURI();

    // create feature map for the transducer
    FeatureMap transducerFeatureMap = Factory.newFeatureMap();
    try {
      // set the grammar location
      transducerFeatureMap.put("grammarURL", japeURI.toURL());
      // set the grammar encoding
      transducerFeatureMap.put("encoding", "UTF-8");
    } catch (MalformedURLException e) {
      System.out.println("Malformed URL of JAPE grammar");
      System.out.println(e.toString());
    }

    ProcessingResource japeTransducer = (ProcessingResource) Factory.createResource("gate.creole.Transducer", transducerFeatureMap);

    SerialAnalyserController pipeline = (SerialAnalyserController) Factory.createResource("gate.creole.SerialAnalyserController");
    pipeline.add(documentResetPR);
    pipeline.add(tokenizerPR);
    pipeline.add(gazetteer);
    pipeline.add(japeTransducer);

    return pipeline;
  }



  public SentimentData computeSentiment(List<CommentedPhoto> photos) throws GateException
  {
    init();
    System.out.println("computeSentiment photos count: " + photos.size());
    if (photos.size() == 0) {
      return new SentimentData();
    }

//    SerialAnalyserController pipeline = createPipeline();
    Corpus corpus = Factory.newCorpus("");

    for (CommentedPhoto photo : photos) {
      List<Comment> comments = photo.getComments();
      for (Comment comment : comments) {
        corpus.add(Factory.newDocument(comment.getText()));
      }
    }

    if (corpus.size() == 0) {
      return new SentimentData();
    }

//    Document document = Factory.newDocument("Nice love good bad hate terrible");
//    Document document2 = Factory.newDocument("Good blablala");
//    Document document3 = Factory.newDocument("Great example");
//// create a corpus and add the document
//    corpus.add(document);
//    corpus.add(document2);
//    corpus.add(document3);

    pipeline.setCorpus(corpus);

    pipeline.execute();

    int positiveCount = 0;
    int negativeCount = 0;

    for (int i = 0; i < corpus.size(); i++) {
      Document doc = corpus.get(i);

      // get the default annotation set for the document
      AnnotationSet as_default = doc.getAnnotations();
      FeatureMap featureMap = null;

      // get all Positive annotations
      AnnotationSet positiveAnnotations = as_default.get("Positive",featureMap);
      // get all Negative annotations
      AnnotationSet negativeAnnotations = as_default.get("Negative",featureMap);

      positiveCount += positiveAnnotations.size();
      negativeCount += negativeAnnotations.size();

      // print out the number of Token annotations.
      System.out.println("Number of Positive annotations:" + positiveAnnotations.size());
      System.out.println("Number of Negative annotations:" + negativeAnnotations.size());

      ArrayList tokenAnnotations = new ArrayList(positiveAnnotations);

//      // looop through the Token annotations
//      for(int j = 0; j < tokenAnnotations.size(); ++j) {
//
//        // get a Token annotation
//        Annotation token = (Annotation)tokenAnnotations.get(j);
//
//        // get features of a Token
//        FeatureMap annFM = token.getFeatures();
//
//        // get the value of the "string" feature and print it in the console
//        String value = (String)annFM.get((Object)"string");
//        System.out.println(value);
//      }
    }

    double sentiment = (double)(positiveCount - negativeCount) / corpus.size();
    SentimentData data = new SentimentData(sentiment, photos.size(), corpus.size(), positiveCount, negativeCount, photos.get(0).getPhoto());

    return data;
  }
}
