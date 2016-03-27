package cz.cvut.fit.vmw.slamasimon.flickr.model;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.comments.Comment;

import java.util.List;

/**
 * Created by simon.
 */
public class CommentedPhoto
{
  private Photo photo;
  private List<Comment> comments;

  public CommentedPhoto(Photo photo, List<Comment> comments)
  {
    this.photo = photo;
    this.comments = comments;
  }

  public Photo getPhoto()
  {
    return photo;
  }

  public void setPhoto(Photo photo)
  {
    this.photo = photo;
  }

  public List<Comment> getComments()
  {
    return comments;
  }

  public void setComments(List<Comment> comments)
  {
    this.comments = comments;
  }
}
