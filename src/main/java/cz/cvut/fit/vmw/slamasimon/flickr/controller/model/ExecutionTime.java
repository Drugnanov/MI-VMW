package cz.cvut.fit.vmw.slamasimon.flickr.controller.model;

/**
 * Created by Drugnanov on 5.1.2016.
 */
public class ExecutionTime {
  private long executionTime;
  private long downloadTime;
  private long rerankingTime;
  private String ratioDownRerank;

  public long getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(long executionTime) {
    this.executionTime = executionTime;
  }

  public long getDownloadTime() {
    return downloadTime;
  }

  public void setDownloadTime(long downloadTime) {
    this.downloadTime = downloadTime;
  }

  public long getRerankingTime() {
    return rerankingTime;
  }

  public void setRerankingTime(long rerankingTime) {
    this.rerankingTime = rerankingTime;
  }

  public String getRatioDownRerank() {
    return ratioDownRerank;
  }

  public void setRatioDownRerank(String ratioDownRerank) {
    this.ratioDownRerank = ratioDownRerank;
  }
}
