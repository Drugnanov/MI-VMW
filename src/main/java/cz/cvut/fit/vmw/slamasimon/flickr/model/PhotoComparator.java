package cz.cvut.fit.vmw.slamasimon.flickr.model;

import java.util.Comparator;

public class PhotoComparator implements Comparator<RankedPhoto> {

	@Override
	public int compare(RankedPhoto p1, RankedPhoto p2) {
//		return -1;
		return p1.getRank().compareTo(p2.getRank());
	}
	
}