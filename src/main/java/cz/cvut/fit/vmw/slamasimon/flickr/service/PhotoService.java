package cz.cvut.fit.vmw.slamasimon.flickr.service;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.GeoData;
import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SearchData;
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

import java.util.*;

public class PhotoService {

  private FlickrService flickrService;
  private int flickrPageSice;

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
            new Date(),
            1, 1, 1, 1
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

    List<RankedPhoto> orderedPhotos = new ArrayList<RankedPhoto>(pdh.getRankedPhotos());
    Collections.sort(orderedPhotos,  new PhotoComparator());

    tm.stop();

    return orderedPhotos;
  }


  //    PriorityQueue orderedPhotos = new PriorityQueue<RankedPhoto>(pdh.getRankedPhotos().size(), new PhotoComparator());
//    orderedPhotos.addAll(pdh.getRankedPhotos());
//    Arrays.sort(orderedPhotos.toArray());

//    List<RankedPhoto> items = new ArrayList<RankedPhoto>(orderedPhotos);
//    String idsOrderedString = "";
//    for (RankedPhoto rankedPhoto : orderedPhotos) {
//      idsOrderedString += rankedPhoto.photo.getId() + "(" + rankedPhoto.getRank()+ ");";
//    }
//    System.out.println("Id Ordered:" +idsOrderedString);

//    String idsPhdString = "";
//    for (RankedPhoto rankedPhoto : pdh.getRankedPhotos()) {
//      idsPhdString += rankedPhoto.photo.getId() + "(" + rankedPhoto.getRank()+ ");";
//    }
//    System.out.println("Id PHD:" +idsPhdString);


  //    List<Integer> tasks = new ArrayList<Integer>();
//    int pageCount = (int) Math.ceil((double) count / flickrPageSice);
//    try {
//      for (int i = 1; i <= pageCount; i++) {
//        int pageLimit = flickrPageSice;
//        if (i == pageCount) {
//          pageLimit = count - (i - 1) * flickrPageSice;
//        }
//        JobFlickrPhotoDownload job = new JobFlickrPhotoDownload(flickrService, text, pageLimit, pageCount);
//        tasks.add(job.getRequestNumber());
//        processManager.addJob(job, job.getRequestNumber());
//      }
//    }
//    finally {
//      //ToDo destory query;
//    }
//
//    while (!tasks.isEmpty()) {
//      for (int i = 0; i < tasks.size(); i++) {
//        Object photoList = processManager.getJobState((int) tasks.get(i));
//        if (photoList != null){
//          list.addAll((PhotoList<Photo>) photoList);
//          tasks.remove(tasks.get(i));
//        }
//      }
//    }

//	public Queue<Photo> getPhotos(String keyword, Color color, Integer limit) {
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
//
//		Queue<Photo> orderedPhotos = new PriorityQueue<Photo>(limit, new PhotoComparator());
//
//		CubbyHole cubbyHole = new CubbyHole();
//
//		PhotoMetadataProducer producer = new PhotoMetadataProducer(cubbyHole, fileLogger, restClient, keyword, limit);
//		PhotoMetadataConsumer consumer = new PhotoMetadataConsumer(cubbyHole, fileLogger, color, similarityRanker);
//		producer.start();
//		consumer.start();
//
//		try {
//			producer.join();
//			consumer.join();
//		} catch (InterruptedException ex) {
//			Logger.getLogger(FlickrService.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		orderedPhotos.addAll(cubbyHole.getRankedPhotos());
//
//		stopWatch.stop();
//		fileLogger.log("whole: " + stopWatch.getDuration() + " limit: " + limit);
//
//		return orderedPhotos;
//	}
}
