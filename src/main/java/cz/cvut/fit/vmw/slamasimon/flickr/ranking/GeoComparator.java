package cz.cvut.fit.vmw.slamasimon.flickr.ranking;

/**
 * Created by simon on 20.11.15.
 */
public class GeoComparator
{
	public static final double EARTH_RADIUS = 6371;

	public double greatCircleDistance(double lat1, double long1, double lat2, double long2)
	{
		double dLat = Math.toRadians(lat2 - lat1);
		double dLong = Math.toRadians(long2 - long1);
		double sindLat = Math.sin(dLat / 2);
		double sindLong = Math.sin(dLong / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLong, 2)
				* Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = EARTH_RADIUS * c;

		return dist;
	}
}
