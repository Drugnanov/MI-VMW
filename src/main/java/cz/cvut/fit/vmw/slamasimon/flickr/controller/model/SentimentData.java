package cz.cvut.fit.vmw.slamasimon.flickr.controller.model;

import com.flickr4java.flickr.photos.Photo;

/**
 * Created by simon.
 */
public class SentimentData
{
  private double sentiment;
  private String keyword;
  private int photosCount;
  private int commentsCount;
  private int positiveCount;
  private int negativeCount;
  private Photo thumbnail;

  public SentimentData(double sentiment, int photosCount, int commentsCount, int positiveCount, int negativeCount, Photo thumbnail)
  {
    this.sentiment = sentiment;
    this.photosCount = photosCount;
    this.commentsCount = commentsCount;
    this.positiveCount = positiveCount;
    this.negativeCount = negativeCount;
    this.thumbnail = thumbnail;
  }

  public SentimentData()
  {
    this.sentiment = 0;
    this.photosCount = 0;
    this.commentsCount = 0;
    this.positiveCount = 0;
    this.negativeCount = 0;
  }

  public double getSentiment()
  {
    return sentiment;
  }

  public void setSentiment(double sentiment)
  {
    this.sentiment = sentiment;
  }

  public String getKeyword()
  {
    return keyword;
  }

  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }

  public int getPhotosCount()
  {
    return photosCount;
  }

  public void setPhotosCount(int photosCount)
  {
    this.photosCount = photosCount;
  }

  public int getCommentsCount()
  {
    return commentsCount;
  }

  public void setCommentsCount(int commentsCount)
  {
    this.commentsCount = commentsCount;
  }

  public int getPositiveCount()
  {
    return positiveCount;
  }

  public void setPositiveCount(int positiveCount)
  {
    this.positiveCount = positiveCount;
  }

  public int getNegativeCount()
  {
    return negativeCount;
  }

  public void setNegativeCount(int negativeCount)
  {
    this.negativeCount = negativeCount;
  }

  public Photo getThumbnail()
  {
    return thumbnail;
  }

  public void setThumbnail(Photo thumbnail)
  {
    this.thumbnail = thumbnail;
  }
}
