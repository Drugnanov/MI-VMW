package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import cz.cvut.fit.vmw.slamasimon.flickr.model.FlikrPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.GateService;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drugnanov on 19.11.2015.
 */
public class FlickrDownloadConsument extends Thread {

  private ProcessDataHolder pdh;
  private Ranker ranker;
  private GateService gateService;
  private UserValues values;
  private List<Thread> threadPool = new ArrayList<Thread>();
  private List<TimeMeasure> tms = new ArrayList<TimeMeasure>();

  public FlickrDownloadConsument(ProcessDataHolder pdh, Ranker ranker, GateService gateService, UserValues values) {
    this.pdh = pdh;
    this.ranker = ranker;
    this.values = values;
    this.gateService = gateService;
  }

  @Override
  public void run() {
    while (pdh.hasUnrankedPhotos()) {
      FlikrPhoto photo = pdh.getNextUnrankedPhoto();
      RankingPhotoThread thread = new RankingPhotoThread(photo, ranker, gateService, values, pdh);
      thread.start();
      threadPool.add(thread);
    }

    for (Thread thread : threadPool) {
      try {
        try {
          thread.join();
          tms.add(((RankingPhotoThread) thread).getTm());
        } catch (InterruptedException ex) {
          System.out.println("Something gets wrong.");
          ex.printStackTrace();
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public List<TimeMeasure> getTms() {
    return tms;
  }
}
