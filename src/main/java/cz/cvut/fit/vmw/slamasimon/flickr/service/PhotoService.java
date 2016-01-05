package cz.cvut.fit.vmw.slamasimon.flickr.service;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.GeoData;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.ExecutionTime;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SearchData;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.TimeMeasures;
import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerException;
import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerMessageException;
import cz.cvut.fit.vmw.slamasimon.flickr.model.PhotoComparator;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.GeoComparator;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.StringComparator;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.FlickrDownloadConsument;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.FlickrDownloadProducent;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.ProcessDataHolder;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhotoService {

  private FlickrService flickrService;
  private int flickrPageSice;
  private TimeMeasures timeTracks = new TimeMeasures();

  public PhotoService(FlickrService flickrService, int flickrPageSize) {
    this.flickrService = flickrService;
    this.flickrPageSice = flickrPageSize;
  }

  public boolean checkFlickr() {
    return flickrService.ping();
  }

  public List<RankedPhoto> search(SearchData searchData)
      throws FlickrException, FlickerMessageException, FlickerException {
    TimeMeasure tm = new TimeMeasure();

    String text = searchData.getTag();
    int count = searchData.getMaxNumberOfPhotos();

    UserValues userValues = new UserValues(
            searchData.getDescription(),
            (int)searchData.getViews(),
            new GeoData(Double.toString(searchData.getLongitude()), Double.toString(searchData.getLatitude()), "1"),
            searchData.getCreatedAtD(),
            searchData.getDescriptionWeight(),
            searchData.getViewsCountWeight(),
            searchData.getGeoWeight(),
            searchData.getCreatedAtWeight()
    );

    ProcessDataHolder pdh = new ProcessDataHolder();

    FlickrDownloadProducent producer = new FlickrDownloadProducent(pdh, flickrService, text, count, flickrPageSice);
    FlickrDownloadConsument consumer = new FlickrDownloadConsument(pdh, new Ranker(new StringComparator(), new GeoComparator()), userValues);
    producer.run();
    consumer.run();

    try {
      producer.join();
      consumer.join();
    } catch (InterruptedException ex) {
      System.out.println("Something went wrong");
    }
    timeTracks.setDownloadTime(producer.getTm(), producer.getDownloadedPhotos());
    timeTracks.setRerankingTimes(consumer.getTms());

    List<RankedPhoto> orderedPhotos = new ArrayList<RankedPhoto>(pdh.getRankedPhotos());
    Collections.sort(orderedPhotos,  new PhotoComparator());

    tm.stop();
    timeTracks.setSearchTime(tm);
    return orderedPhotos;
  }

  public ExecutionTime getExecutionTimes() {
    return (timeTracks == null)? null : timeTracks.getExecutionTimes();
  }
}
