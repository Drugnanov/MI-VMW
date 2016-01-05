package cz.cvut.fit.vmw.slamasimon.flickr.controller.model;

import cz.cvut.fit.vmw.slamasimon.flickr.util.TimeMeasure;

import java.util.List;

/**
 * Created by Drugnanov on 5.1.2016.
 */
public class TimeMeasures {

  private TimeMeasure searchTime;
  private TimeMeasure downloadTime;
  private int downloadedPhotosCount;
  private List<TimeMeasure> rerankingTimes;

  public TimeMeasure getSearchTime() {
    return searchTime;
  }

  public void setSearchTime(TimeMeasure searchTime) {
    this.searchTime = searchTime;
  }

  public TimeMeasure getDownloadTime() {
    return downloadTime;
  }

  public void setDownloadTime(TimeMeasure downloadTime, int downloadedPhotos) {
    this.downloadTime = downloadTime;
    this.downloadedPhotosCount = downloadedPhotos;
  }

  public List<TimeMeasure> getRerankingTimes() {
    return rerankingTimes;
  }

  public void setRerankingTimes(List<TimeMeasure> rerankingTimes) {
    this.rerankingTimes = rerankingTimes;
  }

  public ExecutionTime getExecutionTimes() {
    ExecutionTime et = new ExecutionTime();
    et.setExecutionTime(searchTime.getDuration());
    long downloadTimePerPhoto = downloadTime.getDuration() / this.downloadedPhotosCount;
    et.setDownloadTime(downloadTimePerPhoto);
    Long rerankingTimePerPhoto = 0L;
    for (TimeMeasure tm : rerankingTimes) {
      rerankingTimePerPhoto += tm.getDuration();
    }
    if (rerankingTimes.size() > 0) {
      rerankingTimePerPhoto = rerankingTimePerPhoto / rerankingTimes.size();
    }
    et.setRerankingTime(rerankingTimePerPhoto);
    String rating = "";
    if (downloadTimePerPhoto > rerankingTimePerPhoto){
      rating = Math.round(downloadTimePerPhoto / rerankingTimePerPhoto) + "/" + 1;
    }
    else{
      rating =  1 + "/" + Math.round(rerankingTimePerPhoto / downloadTimePerPhoto);
    }
    et.setRatioDownRerank(rating);
    return et;
  }
}