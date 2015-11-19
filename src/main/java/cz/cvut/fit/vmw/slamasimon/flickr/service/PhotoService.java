package cz.cvut.fit.vmw.slamasimon.flickr.service;

import com.flickr4java.flickr.FlickrException;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;
import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerException;
import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerMessageException;
import cz.cvut.fit.vmw.slamasimon.flickr.model.PhotoComparator;
import cz.cvut.fit.vmw.slamasimon.flickr.model.PhotoRanked;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.Ranker;
import cz.cvut.fit.vmw.slamasimon.flickr.ranking.UserValues;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.FlickrDownloadConsument;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.FlickrDownloadProducent;
import cz.cvut.fit.vmw.slamasimon.flickr.service.parallel.ProcessDataHolder;
import cz.cvut.fit.vmw.slamasimon.flickr.service.queue.ProcessManager;
import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

public class PhotoService {

  private FlickrService flickrService;
  private int flickrPageSice;
  private ProcessManager processManager;

  public PhotoService(FlickrService flickrService, int flickrPageSize, ProcessManager processManager) {
    this.flickrService = flickrService;
    this.flickrPageSice = flickrPageSize;
    this.processManager = processManager;
  }

  public boolean checkFlickr() {
    return flickrService.ping();
  }

  public SortedMultiset<PhotoRanked> search(String text, int count)
      throws FlickrException, FlickerMessageException, FlickerException {
    TimeMeasure tm = new TimeMeasure();

    SortedMultiset<PhotoRanked> orderedPhotos = TreeMultiset.create(new PhotoComparator());

    ProcessDataHolder pdh = new ProcessDataHolder();

    FlickrDownloadProducent producer = new FlickrDownloadProducent(pdh, flickrService, text, count, flickrPageSice);
    FlickrDownloadConsument consumer = new FlickrDownloadConsument(pdh, new Ranker(), new UserValues());
    producer.start();
    consumer.start();

    try {
      producer.join();
      consumer.join();
    } catch (InterruptedException ex) {
      System.out.println("Something gets wrong");
    }

    orderedPhotos.addAll(pdh.getRankedPhotos());
    tm.stop();

    return orderedPhotos;
  }

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
