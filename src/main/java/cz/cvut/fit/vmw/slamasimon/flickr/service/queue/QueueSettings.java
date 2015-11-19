package cz.cvut.fit.vmw.slamasimon.flickr.service.queue;

public class QueueSettings {

  private int corePoolSize = 4;
  private int maximumPoolSize = 8;
  private int keepAliveTime = 5000;
  private int shutDownTimeout = 60;

  public int getCorePoolSize() {
    return corePoolSize;
  }

  public void setCorePoolSize(int corePoolSize) {
    this.corePoolSize = corePoolSize;
  }

  public int getMaximumPoolSize() {
    return maximumPoolSize;
  }

  public void setMaximumPoolSize(int maximumPoolSize) {
    this.maximumPoolSize = maximumPoolSize;
  }

  public int getKeepAliveTime() {
    return keepAliveTime;
  }

  public void setKeepAliveTime(int keepAliveTime) {
    this.keepAliveTime = keepAliveTime;
  }

  public int getShutDownTimeout() {
    return shutDownTimeout;
  }

  public void setShutDownTimeout(int shutDownTimeout) {
    this.shutDownTimeout = shutDownTimeout;
  }
}
