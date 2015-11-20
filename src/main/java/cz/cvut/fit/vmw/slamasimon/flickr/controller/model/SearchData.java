package cz.cvut.fit.vmw.slamasimon.flickr.controller.model;

/**
 * Created by Drugnanov on 17.11.2015.
 */
public class SearchData {

  private String tag = "dog";
  private double latitude;
  private double longitude;
  private long views;
  private String description;
  private int maxNumberOfPhotos = 10;

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public long getViews() {
    return views;
  }

  public void setViews(long views) {
    this.views = views;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getMaxNumberOfPhotos() {
    return maxNumberOfPhotos;
  }

  public void setMaxNumberOfPhotos(int maxNumberOfPhotos) {
    this.maxNumberOfPhotos = maxNumberOfPhotos;
  }
}
