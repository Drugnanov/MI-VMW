package cz.cvut.fit.vmw.slamasimon.flickr.controller.model;

import java.util.Date;

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
  private Date createdAt;

  private double descriptionWeight = 1;
  private double viewsCountWeight = 1;
  private double geoWeight = 1;
  private double createdAtWeight = 1;

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public double getDescriptionWeight()
  {
    return descriptionWeight;
  }

  public void setDescriptionWeight(double descriptionWeight)
  {
    this.descriptionWeight = descriptionWeight;
  }

  public double getViewsCountWeight()
  {
    return viewsCountWeight;
  }

  public void setViewsCountWeight(double viewsCountWeight)
  {
    this.viewsCountWeight = viewsCountWeight;
  }

  public double getGeoWeight()
  {
    return geoWeight;
  }

  public void setGeoWeight(double geoWeight)
  {
    this.geoWeight = geoWeight;
  }

  public double getCreatedAtWeight()
  {
    return createdAtWeight;
  }

  public void setCreatedAtWeight(double createdAtWeight)
  {
    this.createdAtWeight = createdAtWeight;
  }
}
