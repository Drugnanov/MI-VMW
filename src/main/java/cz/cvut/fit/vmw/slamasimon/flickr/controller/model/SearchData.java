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
  private Date createdAtDate = new Date();

  private int descriptionWeight;
  private int viewsCountWeight;
  private int geoWeight;
  private int createdAtWeight;

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

  public int getDescriptionWeight() {
    return descriptionWeight;
  }

  public void setDescriptionWeight(int descriptionWeight) {
    this.descriptionWeight = descriptionWeight;
  }

  public int getViewsCountWeight() {
    return viewsCountWeight;
  }

  public void setViewsCountWeight(int viewsCountWeight) {
    this.viewsCountWeight = viewsCountWeight;
  }

  public int getGeoWeight() {
    return geoWeight;
  }

  public void setGeoWeight(int geoWeight) {
    this.geoWeight = geoWeight;
  }

  public int getCreatedAtWeight() {
    return createdAtWeight;
  }

  public void setCreatedAtWeight(int createdAtWeight) {
    this.createdAtWeight = createdAtWeight;
  }

  public Date getCreatedAtDate() {
    return createdAtDate;
  }

  public void setCreatedAtDate(Date createdAtDate) {
    this.createdAtDate = createdAtDate;
  }
}
