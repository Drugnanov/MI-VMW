package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.photos.Photo;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.RankingPhotoThread;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.FlickrService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Drugnanov on 19.11.2015.
 */
public class FlickrDownloadConsument extends Thread {

  private ProcessDataHolder pdh;
  private Ranker ranker;
  private UserValues values;
  private List<Thread> threadPool = new ArrayList<Thread>();

  public FlickrDownloadConsument(ProcessDataHolder pdh, Ranker ranker, UserValues values) {
    this.pdh = pdh;
    this.ranker = ranker;
    this.values = values;
  }

  @Override
  public void run() {
    while (pdh.hasUnrankedPhotos()) {
      Photo photo = pdh.getNextUnrankedPhoto();
      RankingPhotoThread thread = new RankingPhotoThread(photo, ranker, values, pdh);
      thread.start();
      threadPool.add(thread);
    }

    for (Thread thread : threadPool) {
      try {
        thread.join();
      } catch (InterruptedException ex) {
        System.out.println("Something gets wrong.");
        ex.printStackTrace();
      }
    }
  }
}
