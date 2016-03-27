package cz.cvut.fit.vmw.slamasimon.flickr.service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.photos.comments.Comment;
import com.flickr4java.flickr.test.TestInterface;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class FlickrService {

  private Flickr flickr;
  private SearchParameters searchParameters;

  public FlickrService(Flickr flickr, SearchParameters searchParameters) {
    this.flickr = flickr;
    this.searchParameters = searchParameters;
  }

  public boolean ping() {
    TestInterface testInterface = flickr.getTestInterface();
    try {
      Collection results = testInterface.echo(Collections.EMPTY_MAP);
    }
    catch (FlickrException e) {
      return false;
    }
    return true;
  }

  public PhotoList<Photo> search(String text, int pageLimit, int pageOrder) throws FlickrException {
    searchParameters.setText(text);
    return flickr.getPhotosInterface().search(searchParameters, pageLimit, pageOrder);
  }

  public List<Comment> getComments(String photoId) throws FlickrException
  {
    return flickr.getCommentsInterface().getList(photoId);
  }
}
