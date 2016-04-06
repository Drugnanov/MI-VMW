package cz.cvut.fit.vmw.slamasimon.flickr.model;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.comments.Comment;

import java.util.List;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class FlikrPhoto {

  private Photo photo;
  private List<Comment> commentList;

  public FlikrPhoto(Photo photo) {
    this.photo = photo;
  }

  public FlikrPhoto(Photo photo, List<Comment> commentList) {
    this.photo = photo;
    this.commentList = commentList;
  }

  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }
}
