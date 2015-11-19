package cz.cvut.fit.vmw.slamasimon.flickr.service.queue;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import cz.cvut.fit.vmw.slamasimon.flickr.service.FlickrService;

import java.util.concurrent.Callable;

public class JobFlickrPhotoDownload implements Callable<Object>, Comparable<JobFlickrPhotoDownload> {

  protected FlickrService flickrService;
  protected int pageLimit;
  protected int pageOrder;
  protected String text;
  protected int requestNumber;

  protected static int id = 0;

  public JobFlickrPhotoDownload(FlickrService flickrService, String text, int pageLimit, int pageOrder) {
    this.flickrService = flickrService;
    this.pageLimit = pageLimit;
    this.pageOrder = pageOrder;
    this.text = text;
    this.requestNumber = id++;
  }

  public int getRequestNumber(){
    return this.requestNumber;
  }

  @Override
  public PhotoList<Photo> call() throws FlickrException {
    return flickrService.search(text, pageLimit, pageOrder);
  }

  @Override
  public int compareTo(JobFlickrPhotoDownload o) {
    return Integer.compare(pageOrder, o.pageOrder);
  }
}
