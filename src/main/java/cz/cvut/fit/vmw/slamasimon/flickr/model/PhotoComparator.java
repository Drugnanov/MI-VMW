package cz.cvut.fit.vmw.slamasimon.flickr.model;

import java.util.Comparator;

public class PhotoComparator implements Comparator<PhotoRanked> {

	@Override
	public int compare(PhotoRanked p1, PhotoRanked p2) {
		return -1;
//		return p1.getRank().compareTo(p2.getRank());
	}
	
}