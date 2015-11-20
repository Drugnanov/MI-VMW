package cz.cvut.fit.vmw.slamasimon.flickr.controller;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.*;
import com.google.common.collect.SortedMultiset;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SearchData;
import cz.cvut.fit.vmw.slamasimon.flickr.model.PhotoRanked;
import cz.cvut.fit.vmw.slamasimon.flickr.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/")
public class FlickrController {

  private final static String ROOT_NAVIGATION = "/flickr/search";
  private final static String MODEL_SEARCH_DATA = "searchData";
  private final static String MODEL_FLICKR_TEST = "flickrTest";
  private final static String MODEL_SEARCH_RESULT_PHOTOS = "photos";
  private final static String MODEL_SEARCH_RESULT_PHOTO = "photoResult";
  private final static String MODEL_SEARCH_FOUND_NUMBER = "photosFoundNumber";

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
    searchData.setTag("dog");
    return searchWithResultsPost(model, searchData, request);
  }

  @RequestMapping(value = {ROOT_NAVIGATION}, method = RequestMethod.POST)
  public String searchWithResultsPost(ModelMap model, SearchData searchData, HttpServletRequest request)
      throws Exception {

    SortedMultiset<PhotoRanked> photosSet = ps.search(searchData.getTag(), 10);
    int numberOfFound = photosSet.size();
    model.addAttribute(MODEL_SEARCH_FOUND_NUMBER, numberOfFound);
    model.addAttribute(MODEL_SEARCH_RESULT_PHOTOS, photosSet);
    return "photos";
  }

  public PhotoService getPs() {
    return ps;
  }

  public void setPs(PhotoService ps) {
    this.ps = ps;
  }
}

//String apiKey = "d61cc5374a430814bffbf4bad756a3cb";
//String sharedSecret = "5bf1bb7c5ffe55a4";
//Flickr f = new Flickr(apiKey, sharedSecret, new REST());
//SearchParameters searchParameters = new SearchParameters();
////    searchParameters.setHasGeo(true);
//Set<String> extra = new LinkedHashSet<String>();
////    extra.add("owner_name");
////    extra.add("tags");
//extra.add("geo");
//    extra.add("views");
//    extra.add("description");
//    extra.add("date_taken");
//    searchParameters.setExtras(extra);
////    searchParameters.setText("klánovice");
//    searchParameters.setText(searchData.getTag());
//    searchParameters.setSort(SearchParameters.RELEVANCE);