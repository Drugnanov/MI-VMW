package cz.cvut.fit.vmw.slamasimon.flickr.model;

import com.flickr4java.flickr.photos.Photo;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class PhotoRanked{
  Double rank = 0.0;
  public Photo fphoto;

  public PhotoRanked(Photo photo) {
    this.fphoto = photo;
  }

  public Double getRank() {
    return rank;
  }

  public void setRank(Double rank) {
    this.rank = rank;
  }

  public Photo getFphoto() {
    return fphoto;
  }

  public void setFphoto(Photo fphoto) {
    this.fphoto = fphoto;
  }
}
