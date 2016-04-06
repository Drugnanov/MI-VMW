package cz.cvut.fit.vmw.slamasimon.flickr.model;

/**
 * Created by Drugnanov on 3.4.2016.
 */
public class SentimentInfo {

  private double sentiment;
  private int commentsCount;
  private int positiveCount;
  private int negativeCount;

  public SentimentInfo(double sentiment, int commentsCount, int positiveCount, int negativeCount) {
    this.sentiment = sentiment;
    this.commentsCount = commentsCount;
    this.positiveCount = positiveCount;
    this.negativeCount = negativeCount;
  }

  public SentimentInfo() {
    this.sentiment = 0;
    this.commentsCount = 0;
    this.positiveCount = 0;
    this.negativeCount = 0;
  }

  public double getSentiment() {
    return sentiment;
  }

  public void setSentiment(double sentiment) {
    this.sentiment = sentiment;
  }

  public int getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(int commentsCount) {
    this.commentsCount = commentsCount;
  }

  public int getPositiveCount() {
    return positiveCount;
  }

  public void setPositiveCount(int positiveCount) {
    this.positiveCount = positiveCount;
  }

  public int getNegativeCount() {
    return negativeCount;
  }

  public void setNegativeCount(int negativeCount) {
    this.negativeCount = negativeCount;
  }
}