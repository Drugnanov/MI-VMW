package cz.cvut.fit.vmw.slamasimon.flickr.ranking;

import java.util.Date;

/**
 * Created by Drugnanov on 19.11.2015.
 */
public class UserValues
{
  private String description;
  private int viewsCount;
  private double latitude;
  private double longitude;
  private Date createdAt;

  private double descriptionWeight;
  private double viewsCountWeight;
  private double geoWeight;
  private double createdAtWeight;

  public UserValues()
  {
  }

  public UserValues(String description, int viewsCount, double latitude, double longitude, Date createdAt, double descriptionWeight, double viewsCountWeight, double geoWeight, double createdAtWeight)
  {
    this.description = description;
    this.viewsCount = viewsCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.createdAt = createdAt;
    this.descriptionWeight = descriptionWeight;
    this.viewsCountWeight = viewsCountWeight;
    this.geoWeight = geoWeight;
    this.createdAtWeight = createdAtWeight;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getViewsCount()
  {
    return viewsCount;
  }

  public void setViewsCount(int viewsCount)
  {
    this.viewsCount = viewsCount;
  }

  public double getLatitude()
  {
    return latitude;
  }

  public void setLatitude(double latitude)
  {
    this.latitude = latitude;
  }

  public double getLongitude()
  {
    return longitude;
  }

  public void setLongitude(double longitude)
  {
    this.longitude = longitude;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt)
  {
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
