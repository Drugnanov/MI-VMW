package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.photos.Photo;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;

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
    RankedPhoto rankedPhoto = null;
    try {
      rankedPhoto = ranker.rank(photo, values);
    }
    catch (Exception ex) {
      System.out.println("Something went wrong.");
      ex.printStackTrace();
    }
    finally {
      if (rankedPhoto != null) {
        pdh.putRankedPhoto(rankedPhoto);
      }
    }
  }
}
