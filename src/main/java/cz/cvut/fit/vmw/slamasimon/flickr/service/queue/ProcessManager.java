package cz.cvut.fit.vmw.slamasimon.flickr.service.queue;

import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerException;
import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerMessageException;

import java.util.concurrent.Callable;

public interface ProcessManager {

  /**
   * Check state of job in the queue
   */
  public Object getJobState(Integer requestNumber, int timeoutSeconds)
      throws FlickerMessageException, FlickerException;

  /**
   * Same as getJobState(Long requestNumber, int timeoutSeconds) using default timeout configuration
   */
  public Object getJobState(Integer requestNumber)
      throws FlickerMessageException, FlickerException;

  /**
   * Add new job to the queue
   */
  public void addJob(Callable<Object> job, Integer requestNumber) throws FlickerMessageException;

  /**
   * Check if job exists in the queue
   */
  public boolean jobExists(Integer requestNumber);

  /** Shutdown method for spring lifecycle */
  public void shutdownAndAwaitTermination();
}
