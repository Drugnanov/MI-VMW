package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.comments.Comment;
import cz.cvut.fit.vmw.slamasimon.flickr.model.CommentedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.FlickrService;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.List;

public class CommentsPhotoThread extends Thread {

  private Photo photo;
  private ProcessDataHolder pdh;
  private TimeMeasure tm;
  private FlickrService flickrService;

  public CommentsPhotoThread(Photo photo, ProcessDataHolder pdh, FlickrService flickrService) {
    this.photo = photo;
    this.pdh = pdh;
    this.flickrService = flickrService;
  }

  @Override
  public void run() {
    CommentedPhoto commentedPhoto = null;
    tm = new TimeMeasure();
    try {
      List<Comment> comments = flickrService.getComments(photo.getId());
      commentedPhoto = new CommentedPhoto(photo, comments);
    }
    catch (Exception ex) {
      System.out.println("Something went wrong.");
      ex.printStackTrace();
    }
    finally {
      if (commentedPhoto != null) {
        pdh.putCommentedPhoto(commentedPhoto);
      }
      tm.stop();
    }
  }

  public TimeMeasure getTm() {
    return tm;
  }
}
