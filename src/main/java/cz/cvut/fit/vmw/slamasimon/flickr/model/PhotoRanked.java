package cz.cvut.fit.vmw.slamasimon.flickr.model;

import com.flickr4java.flickr.photos.Photo;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class PhotoRanked{
  Double rank = 0.0;
  public Photo photo;

  public PhotoRanked(Photo photo) {
    this.photo = photo;
  }

  public Double getRank() {
    return rank;
  }

  public void setRank(Double rank) {
    this.rank = rank;
  }
}
