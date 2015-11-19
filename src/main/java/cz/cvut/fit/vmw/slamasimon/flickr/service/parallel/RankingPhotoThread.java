package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.photos.Photo;
import cz.cvut.fit.vmw.slamasimon.flickr.model.PhotoRanked;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.ProcessDataHolder;

public class RankingPhotoThread extends Thread {

  private Photo photo;
  private Ranker ranker;
  private UserValues values;
  private ProcessDataHolder pdh;

  public RankingPhotoThread(Photo photo, Ranker ranker, UserValues values, ProcessDataHolder pdh) {
    this.photo = photo;
    this.ranker = ranker;
    this.values = values;
    this.pdh = pdh;
  }

  @Override
  public void run() {
    PhotoRanked photoRanked = null;
    try {
      photoRanked = new PhotoRanked(photo);
    }
    catch (Exception ex) {
      System.out.println("Something get wrong.");
      ex.printStackTrace();
    }
    finally {
      if (photoRanked != null) {
        pdh.putRankedPhoto(photoRanked);
      }
    }
  }
}
