package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import cz.cvut.fit.vmw.slamasimon.flickr.service.FlickrService;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.ArrayList;
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
  private TimeMeasure tm;
  private int downloadedPhotos = 0;

  public FlickrDownloadProducent(ProcessDataHolder pdh, FlickrService flickrService, String text, int photosLimit,
                                 int pageLimit) {
    this.pdh = pdh;
    this.flickrService = flickrService;
    this.text = text;
    this.photosLimit = photosLimit;
    this.pageLimit = pageLimit;
  }

//  https://www.flickr.com/services/api/explore/flickr.photos.search
  @Override
  public void run() {
    int pagesCount = (int) Math.ceil((double) photosLimit / pageLimit);
    List<String> photosId = new ArrayList<String>();
    tm = new TimeMeasure();
    int photosToAccept = pageLimit;
    for (int i = 1 ; i <= pagesCount; i++) {
      if (i == pagesCount) {
        photosToAccept = photosLimit - (i-1) * pageLimit;
      }
      try {
        PhotoList<Photo> photosList = flickrService.search(text, pageLimit, i);
        if (photosToAccept == pageLimit){
          pdh.putUnrankedPhotos(photosList);
          downloadedPhotos += photosList.size();
          //debug
          for (Photo p : photosList) {
            checkPhoto(p, photosId);
          }
        }
        else {
          for (Photo p : photosList) {
            pdh.putUnrankedPhotos(p);
            downloadedPhotos++;
            checkPhoto(p, photosId);
            photosToAccept--;
            if (photosToAccept == 0) break;
          }
        }

      }
      catch (FlickrException e) {
        System.out.println("Something gets wrong during downloading photos.");
      }
    }
    tm.stop();
    pdh.noMoreUnrankedPhotos();
  }

  private void checkPhoto(Photo p, List<String> photosId) {
    if (photosId.contains(p.getId())) {
      System.out.println("ERROR photos with id " + p.getId() + " was downloaded previously!!");
    }
    else {
      photosId.add(p.getId());
    }
  }

  public TimeMeasure getTm() {
    return tm;
  }

  public int getDownloadedPhotos() {
    return downloadedPhotos;
  }
}
