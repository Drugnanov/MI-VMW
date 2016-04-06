package cz.cvut.fit.vmw.slamasimon.flickr.service;

import com.flickr4java.flickr.photos.comments.Comment;
import cz.cvut.fit.vmw.slamasimon.flickr.model.FlikrPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.model.SentimentInfo;
import gate.*;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Drugnanov on 3.4.2016.
 */
public class GateService {

  private static boolean isInitialized = false;
  private String homePath;
  private String pluginsPath;
  private String sentimentGrammarPath;
  private SerialAnalyserController pipeline;

  public GateService(String homePath, String pluginsPath, String sentimentGrammarPath) {
    this.homePath = homePath;
    this.pluginsPath = pluginsPath;
    this.sentimentGrammarPath = sentimentGrammarPath;
  }

  private synchronized void init() throws GateException {
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
      }
      catch (MalformedURLException e) {
        e.printStackTrace();
      }
      register.registerDirectories(annieHome);

      pipeline = createPipeline();

      isInitialized = true;
    }
  }

  private SerialAnalyserController createPipeline() throws GateException {

    ProcessingResource documentResetPR = (ProcessingResource) Factory
        .createResource("gate.creole.annotdelete.AnnotationDeletePR");
    ProcessingResource tokenizerPR = (ProcessingResource) Factory
        .createResource("gate.creole.tokeniser.DefaultTokeniser");

    FeatureMap gazetteerFeatureMap = Factory.newFeatureMap();
    gazetteerFeatureMap.put("caseSensitive", false);

    ProcessingResource gazetteer = (ProcessingResource) Factory
        .createResource("gate.creole.gazetteer.DefaultGazetteer", gazetteerFeatureMap);

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
    }
    catch (MalformedURLException e) {
      System.out.println("Malformed URL of JAPE grammar");
      System.out.println(e.toString());
    }

    ProcessingResource japeTransducer = (ProcessingResource) Factory
        .createResource("gate.creole.Transducer", transducerFeatureMap);

    SerialAnalyserController pipeline = (SerialAnalyserController) Factory
        .createResource("gate.creole.SerialAnalyserController");
    pipeline.add(documentResetPR);
    pipeline.add(tokenizerPR);
    pipeline.add(gazetteer);
    pipeline.add(japeTransducer);

    return pipeline;
  }


  public synchronized SentimentInfo computeSentiment(FlikrPhoto photo) throws GateException {
    init();

    Corpus corpus = Factory.newCorpus("");

    for (Comment comment : photo.getCommentList()) {
      corpus.add(Factory.newDocument(comment.getText()));
    }

    if (corpus.size() == 0) {
      return new SentimentInfo();
    }

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
      AnnotationSet positiveAnnotations = as_default.get("Positive", featureMap);
      // get all Negative annotations
      AnnotationSet negativeAnnotations = as_default.get("Negative", featureMap);

//      System.out.println(
//          "For doc \"" + doc.getContent() + "\" found positive: " + positiveAnnotations.size() + " and negative:" +
//              negativeAnnotations.size());
      positiveCount += positiveAnnotations.size();
      negativeCount += negativeAnnotations.size();
    }

    double sentiment = 0;
    try {
      sentiment = Math.round(((double) (positiveCount - negativeCount) / corpus.size())*100)/100.0;
    }
    catch (Exception e) {
      System.out.println("Something goes wrong:" + e.getMessage());
    }

    return new SentimentInfo(sentiment, corpus.size(), positiveCount, negativeCount);
  }
}