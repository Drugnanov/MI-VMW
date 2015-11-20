package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import cz.cvut.fit.vmw.slamasimon.flickr.service.FlickrService;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.List;

/**
 * Created by Drugnanov on 19.11.2015.
 */
public class FlickrDownloadProducent extends Thread {

  private ProcessDataHolder pdh;
  private FlickrService flickrService;
  private String text;
  private int photosLimit;
  private int pageLimit;

  public FlickrDownloadProducent(ProcessDataHolder pdh, FlickrService flickrService, String text, int pagesCount,
                                 int pageLimit) {
    this.pdh = pdh;
    this.flickrService = flickrService;
    this.text = text;
    this.photosLimit = pagesCount;
    this.pageLimit = pageLimit;
  }

  @Override
  public void run() {
    int pagesCount = (int) Math.ceil((double) photosLimit / pageLimit);
    for (int i = 1 ; i <= pagesCount; i++) {
      TimeMeasure tm = new TimeMeasure();
//      int pageLimit = this.pageLimit;
//      if (i == (pagesCount - 1)) {
//        pageLimit = photosLimit - (i) * pageLimit;
//      }
      try {
        PhotoList<Photo> photosList = flickrService.search(text, pageLimit, i);
        for (Photo p : photosList) {
          System.out.println("Page i:" + i + " image id:" + p.getId());
        }
        pdh.putUnrankedPhotos(photosList);
      }
      catch (FlickrException e) {
        System.out.println("Something gets wrong during downloading photos.");
      }

      tm.stop();
    }
    pdh.noMoreUnrankedPhotos();
  }
}
