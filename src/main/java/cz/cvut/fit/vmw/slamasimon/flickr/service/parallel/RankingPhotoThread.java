package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import cz.cvut.fit.vmw.slamasimon.flickr.model.FlikrPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.model.SentimentInfo;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.GateService;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

public class RankingPhotoThread extends Thread {

  private FlikrPhoto photo;
  private Ranker ranker;
  private GateService gateService;
  private UserValues values;
  private ProcessDataHolder pdh;
  private TimeMeasure tm;

  public RankingPhotoThread(FlikrPhoto photo, Ranker ranker, GateService gateService, UserValues values, ProcessDataHolder pdh) {
    this.photo = photo;
    this.ranker = ranker;
    this.gateService = gateService;
    this.values = values;
    this.pdh = pdh;
  }

  @Override
  public void run() {
    RankedPhoto rankedPhoto = null;
    tm = new TimeMeasure();
    try {
      SentimentInfo sentimentInfo = gateService.computeSentiment(photo);
      rankedPhoto = ranker.rank(photo, values);
      rankedPhoto.setSentimentInfo(sentimentInfo);
    }
    catch (Exception ex) {
      System.out.println("Something went wrong.");
      ex.printStackTrace();
    }
    finally {
      if (rankedPhoto != null) {
        pdh.putRankedPhoto(rankedPhoto);
      }
      tm.stop();
    }
  }

  public TimeMeasure getTm() {
    return tm;
  }
}
