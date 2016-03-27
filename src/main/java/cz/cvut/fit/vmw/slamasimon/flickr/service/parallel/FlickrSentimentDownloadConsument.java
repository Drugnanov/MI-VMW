package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.photos.Photo;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.FlickrService;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drugnanov on 19.11.2015.
 */
public class FlickrSentimentDownloadConsument extends Thread {

  private FlickrService flickrService;
  private ProcessDataHolder pdh;
  private List<Thread> threadPool = new ArrayList<Thread>();
  private List<TimeMeasure> tms = new ArrayList<TimeMeasure>();

  public FlickrSentimentDownloadConsument(ProcessDataHolder pdh, FlickrService flickrService) {
    this.pdh = pdh;
    this.flickrService = flickrService;
  }

  @Override
  public void run() {
    while (pdh.hasUnrankedPhotos()) {
      Photo photo = pdh.getNextUnrankedPhoto();
      CommentsPhotoThread thread = new CommentsPhotoThread(photo, pdh, flickrService);
      thread.start();
      threadPool.add(thread);
    }

    for (Thread thread : threadPool) {
      try {
        try {
          thread.join();
          tms.add(((CommentsPhotoThread) thread).getTm());
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
