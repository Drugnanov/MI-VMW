package cz.cvut.fit.vmw.slamasimon.flickr.service.queue;

import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerException;
import cz.cvut.fit.vmw.slamasimon.flickr.exception.FlickerMessageException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

/** manage job processing with threadpoolexecutor */
public class ProcessManagerImp implements ProcessManager {

  //Thread safe
  ConcurrentHashMap<Integer, Future<Object>> jobs = new ConcurrentHashMap<Integer, Future<Object>>();

  private ExecutorService exec = null;
  private QueueSettings queueSettings = null;
  private int queueFutureTimeout;

  @Autowired
  public ProcessManagerImp(QueueSettings qs) {
    this.queueSettings = qs;
    exec = new ThreadPoolExecutor(qs.getCorePoolSize(), qs.getMaximumPoolSize(),
        qs.getShutDownTimeout(), TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>());
  }

  @Override
  public Object getJobState(Integer requestNumber, int timeoutSeconds)
      throws FlickerMessageException, FlickerException {
    if (!jobExists(requestNumber)) {
      throw new FlickerMessageException("vmw.flickr.error.queue.job.missing",
          new Object[] {String.valueOf(requestNumber)});
    }
    Future<Object> jobFuture = jobs.get(requestNumber);
    try {
      Object job = jobFuture.get(timeoutSeconds, TimeUnit.SECONDS);
      jobs.remove(requestNumber);
      return job;
    }
    catch (ExecutionException e) {
      throw new FlickerException("Unknown exception", e);
    }
    catch (InterruptedException e) {
      throw new FlickerMessageException("vmw.flickr.error.queue.job.interrupted", new Object[] {});
    }
    catch (TimeoutException e) {
      return null;
    }
  }

  @Override
  public Object getJobState(Integer requestNumber)
      throws FlickerMessageException, FlickerException {
    return getJobState(requestNumber, queueFutureTimeout);
  }

  @Override
  public void addJob(Callable<Object> job, Integer requestNumber) throws FlickerMessageException {
    if (jobExists(requestNumber)) {
      throw new FlickerMessageException("vmw.flickr.error.queue.job.create.already.exists",
          new Object[] {String.valueOf(requestNumber)});
    }
    Future<Object> jobFuture = exec.submit(job);
    jobs.put(requestNumber, jobFuture);
//    logger.info("New job added into queue request number:" + requestNumber);
  }

  @Override
  public boolean jobExists(Integer requestNumber) {
    Future<Object> jobFuture = jobs.get(requestNumber);
    return jobFuture != null;
  }

  @Override
  public void shutdownAndAwaitTermination() {
    exec.shutdown();
    try {
      if (!exec.awaitTermination(queueSettings.getShutDownTimeout(), TimeUnit.SECONDS)) {
        exec.shutdownNow(); // Cancel currently executing tasks
        // Wait a while for tasks to respond to being cancelled
        if (!exec.awaitTermination(60, TimeUnit.SECONDS)) {
//          logger.warn("QueueProcessor: pool did not terminate.");
        }
      }
    }
    catch (InterruptedException e) {
//      logger.warn("QueueProcessor: ignoring InterruptedException...");
      exec.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  public int getQueueFutureTimeout() {
    return queueFutureTimeout;
  }

  public void setQueueFutureTimeout(int queueFutureTimeout) {
    this.queueFutureTimeout = queueFutureTimeout;
  }
}
