package cz.cvut.fit.vmw.slamasimon.flickr.controller.model;

/**
 * Created by simon.
 */
public class SentimentSearchData
{
  private String keywords;
  private int numOfPhotos = 5;

  public String getKeywords()
  {
    return keywords;
  }

  public void setKeywords(String keywords)
  {
    this.keywords = keywords;
  }

  public int getNumOfPhotos()
  {
    return numOfPhotos;
  }

  public void setNumOfPhotos(int numOfPhotos)
  {
    this.numOfPhotos = numOfPhotos;
  }
}
