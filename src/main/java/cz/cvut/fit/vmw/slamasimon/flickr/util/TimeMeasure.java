package cz.cvut.fit.vmw.slamasimon.flickr.util;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class TimeMeasure {

  private long startTime;
  private long endTime;

  public TimeMeasure() {
    startTime = System.nanoTime();
  }

  public void stop() {
    endTime = System.nanoTime();
  }

  public long getDuration() {
    return endTime - startTime;
  }
}
