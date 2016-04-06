package cz.cvut.fit.vmw.slamasimon.flickr.model;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class RankedPhoto
{
  Double rank = 0.0;
  private SentimentInfo sentimentInfo;
  public FlikrPhoto photo;

  public RankedPhoto(FlikrPhoto photo) {
    this.photo = photo;
  }

  public RankedPhoto(FlikrPhoto photo, Double rank)
  {
    this.photo = photo;
    this.rank = rank;
  }

  public Double getRank() {
    return rank;
  }

  public void setRank(Double rank) {
    this.rank = rank;
  }

  public FlikrPhoto getPhoto() {
    return photo;
  }

  public void setPhoto(FlikrPhoto photo) {
    this.photo = photo;
  }

  public SentimentInfo getSentimentInfo() {
    return sentimentInfo;
  }

  public void setSentimentInfo(SentimentInfo sentimentInfo) {
    this.sentimentInfo = sentimentInfo;
  }
}
