package cz.cvut.fit.vmw.slamasimon.flickr.ranking;

import com.flickr4java.flickr.photos.GeoData;
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

  private double stringLambda = 1;
  private double geoLambda = 1;
  private double intLambda = 1;
  private double dateLambda = 1;

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
    double rank = 0;
    try {
      System.out.printf("First");
      scores[0] = getStringDistance(photo.getDescription(), filters.getDescription())* filters.getDescriptionWeight();
      System.out.println("Second");
      scores[1] = getGeoDistance(photo.getGeoData(), filters.getGeoData()) * filters.getGeoWeight();

      System.out.println("Third");
      scores[2] = getIntDistance(photo.getViews(), filters.getViewsCount()) * filters.getViewsCountWeight();
      System.out.printf("Four");
      scores[3] = getDateDistance(photo.getDateAdded(), filters.getCreatedAt()) * filters.getCreatedAtWeight();
      System.out.println("Five");

      for (double score : scores) {
        rank += score;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return new RankedPhoto(photo, rank);
  }

  private double getStringDistance(String s1, String s2)
  {
    double editDistance = stringComparator.editDistance(s1, s2);
    return normalizeAndDecay(editDistance, stringLambda);
  }

  private double getGeoDistance(GeoData photo, GeoData filter)
  {
    if (filter == null) return photo == null ? 0 : 1;
    if (photo == null) return 1;

    double baseDistance = geoComparator.greatCircleDistance(photo.getLatitude(), photo.getLongitude(),
            filter.getLatitude(), filter.getLongitude());
    return normalizeAndDecay(baseDistance, geoLambda);
  }

  private double getIntDistance(int first, int second)
  {
    return normalizeAndDecay(Math.abs(first - second), intLambda);
  }

  private double getDateDistance(Date first, Date second)
  {
    long diff = Math.abs(first.getTime() - second.getTime());
    long diffDays = diff / (24 * 60 * 60 * 1000);

    return normalizeAndDecay(diffDays, dateLambda);
  }

  private double normalizeAndDecay(double input, double lambda)
  {
    return 1 - Math.pow(Math.E, -lambda * input);
  }
}
