package cz.cvut.fit.vmw.slamasimon.flickr.model;

import com.flickr4java.flickr.photos.Photo;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class RankedPhoto
{
  Double rank = 0.0;
  public Photo photo;

  public RankedPhoto(Photo photo) {
    this.photo = photo;
  }

  public RankedPhoto(Photo photo, Double rank)
  {
    this.photo = photo;
    this.rank = rank;
  }

  public Double getRank() {
    return rank;
  }

  public void setRank(Double rank) {
    this.rank = rank;
  }

  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }
}
