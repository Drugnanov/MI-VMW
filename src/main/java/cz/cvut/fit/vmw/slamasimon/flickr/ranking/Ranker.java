package cz.cvut.fit.vmw.slamasimon.flickr.ranking;

import com.flickr4java.flickr.photos.Photo;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;

import java.util.Date;

/**
 * Created by Drugnanov on 19.11.2015.
 */
public class Ranker
{
  private StringComparator stringComparator;
  private GeoComparator geoComparator;

  public Ranker(StringComparator stringComparator, GeoComparator geoComparator)
  {
    this.stringComparator = stringComparator;
    this.geoComparator = geoComparator;
    // TODO other comparators will follow
  }

  public RankedPhoto rank(Photo photo, UserValues filters)
  {
    System.out.println("ranking started");
    double[] scores = new double[4];
    scores[0] = getStringDistance(photo.getDescription(), filters.getDescription())* filters.getDescriptionWeight();
    scores[1] = getGeoDistance(photo.getGeoData().getLatitude(), photo.getGeoData().getLongitude(),
            filters.getLatitude(), filters.getLongitude()) * filters.getGeoWeight();
    scores[2] = getIntDistance(photo.getViews(), filters.getViewsCount()) * filters.getViewsCountWeight();
    scores[3] = getDateDistance(photo.getDateAdded(), filters.getCreatedAt()) * filters.getCreatedAtWeight();

    double total = 0;
    for (double score : scores) {
      total += score;
    }

    return new RankedPhoto(photo, total);
  }

  private double getStringDistance(String s1, String s2)
  {
    return stringComparator.editDistance(s1, s2);
  }

  private double getGeoDistance(double lat1, double lon1, double lat2, double lon2)
  {
    return geoComparator.greatCircleDistance(lat1, lon1, lat2, lon2);
  }

  private double getIntDistance(int first, int second)
  {

    return 1; // TODO
  }

  private double getDateDistance(Date first, Date second)
  {
    return 1; // TODO
  }
}
