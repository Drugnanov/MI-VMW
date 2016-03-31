package cz.cvut.fit.vmw.slamasimon.flickr.controller;

import com.flickr4java.flickr.FlickrException;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.ExecutionTime;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SearchData;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SentimentData;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SentimentSearchData;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class FlickrController {

  private final static String ROOT_NAVIGATION = "/flickr/search";
  private final static String URL_SENTIMENT_FORM = "/flickr/sentiment";
  private final static String MODEL_SEARCH_DATA = "searchData";
  private final static String MODEL_FLICKR_TEST = "flickrTest";
  private final static String MODEL_SEARCH_RESULT_PHOTOS = "photos";
  private final static String MODEL_SEARCH_RESULT_PHOTO = "photoResult";
  private final static String MODEL_SEARCH_FOUND_NUMBER = "photosFoundNumber";
  private final static String MODEL_SEARCH_ERRORS = "errors";
  private final static String MODEL_SEARCH_EXECUTION_TIME = "executionTimes";
  private final static String MODEL_SENTIMENTS = "sentiments";

  @Autowired
  private PhotoService ps;

  @RequestMapping(method = RequestMethod.GET)
  public String printWelcoms(ModelMap model) throws FlickrException {
    String flickrTest = (ps.checkFlickr()) ? "OK" : "FAIL";
    model.addAttribute(MODEL_FLICKR_TEST, flickrTest);
    model.addAttribute(MODEL_SEARCH_DATA, new SearchData());
    return "index";
  }

  @RequestMapping(value = {ROOT_NAVIGATION}, method = RequestMethod.GET)
  public String searchWithResultsGet(ModelMap model, SearchData searchData, HttpServletRequest request)
      throws Exception {
    return searchWithResultsPost(model, searchData, request);
  }

  @RequestMapping(value = {ROOT_NAVIGATION}, method = RequestMethod.POST)
  public String searchWithResultsPost(ModelMap model, SearchData searchData, HttpServletRequest request)
      throws Exception {

    List<RankedPhoto> photosSet = new ArrayList<RankedPhoto>();
    ExecutionTime tms = null;
    int numberOfFound = 0;

    List<String> errors = checkSearchData(searchData);
    if (errors.size() == 0) {
      try {
        photosSet = ps.search(searchData);
        numberOfFound = photosSet.size();
        tms = ps.getExecutionTimes();
      }
      catch (Exception e) {
        errors.add("Something goes wrong! " + e.getMessage() + " e type:" + e.toString());
      }
    }
    model.addAttribute(MODEL_SEARCH_FOUND_NUMBER, numberOfFound);
    model.addAttribute(MODEL_SEARCH_RESULT_PHOTOS, photosSet);
    model.addAttribute(MODEL_SEARCH_ERRORS, errors);
    model.addAttribute(MODEL_SEARCH_EXECUTION_TIME, tms);
    return "photos";
  }

  private List<String> checkSearchData(SearchData searchData) {
    List<String> errors = new ArrayList<String>();
    if (searchData.getMaxNumberOfPhotos() < 1) {
      errors.add("Bad number of photos to search. Value '" + searchData.getMaxNumberOfPhotos() + "' is invalid.");
    }
    if (searchData.getTag() == null || searchData.getTag().length() <= 2) {
      if (searchData.getTag() == null) {
        errors.add("Search keyword is empty. Its not allowed.");
      }
      if (searchData.getTag().length() <= 2) {
        errors.add(
            "Search keyword with length " + searchData.getTag().length() + " is too short. Please use more than two chars.");
      }
    }
    if (searchData.getCreatedAt() != null && searchData.getCreatedAt().length() > 0){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      try {
        searchData.setCreatedAtD(format.parse(searchData.getCreatedAt()));
      }
      catch (ParseException e) {
        errors.add(
            "Date '" + searchData.getCreatedAt() + "' is invalid. Accepted format is dd.mm.yyyy");
      }
    }
    return errors;
  }

  @RequestMapping(value = {URL_SENTIMENT_FORM}, method = RequestMethod.GET)
  public String getSentiment(ModelMap model, SentimentSearchData searchData, HttpServletRequest request)
          throws Exception {
    return "sentiment";
  }

  @RequestMapping(value = {URL_SENTIMENT_FORM}, method = RequestMethod.POST)
  public String postSentiment(ModelMap model, SentimentSearchData searchData, HttpServletRequest request)
          throws Exception {
    String keywordsString = searchData.getKeywords();
    String[] keywords = keywordsString.split(",");
    int photosCount = searchData.getNumOfPhotos();

//    System.out.println("keyword: " + keyword);
//    System.out.println("Photos count: " + photosCount);

    ExecutionTime tms = null;
    SentimentData sentiment = null;
    List<String> errors = new ArrayList<String>();
    List<SentimentData> sentiments = new ArrayList<SentimentData>();
    try {
      for (int i = 0; i < keywords.length; i++) {
        sentiment = ps.getSentiment(keywords[i], photosCount);
        sentiment.setKeyword(keywords[i]);
        sentiments.add(sentiment);
      }
      tms = ps.getExecutionTimes();
    } catch (Exception e) {
      errors.add("Something went wrong! " + e.getMessage() + ", e type:" + e.toString());
    }
    model.addAttribute(MODEL_SEARCH_ERRORS, errors);
    model.addAttribute(MODEL_SEARCH_EXECUTION_TIME, tms);
    model.addAttribute(MODEL_SENTIMENTS, sentiments);
    return "sentiment";
  }


  public PhotoService getPs() {
    return ps;
  }

  public void setPs(PhotoService ps) {
    this.ps = ps;
  }
}