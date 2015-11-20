package cz.cvut.fit.vmw.slamasimon.flickr.service.parallel;

import com.flickr4java.flickr.photos.Photo;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class ProcessDataHolder {

	private Queue<Photo> unrankedPhotos;
	private Queue<RankedPhoto> rankedPhotos;
	private int unrankedOut = 0;
	private boolean producingUnrankedPhotos = true;

	public ProcessDataHolder() {
		this.unrankedPhotos = new LinkedList<Photo>();
		this.rankedPhotos = new LinkedList<RankedPhoto>();
	}

	public synchronized Photo getNextUnrankedPhoto() {
		while (unrankedPhotos.isEmpty() || unrankedOut > 400) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Something get wrong");
			}
		}
		unrankedOut++;
		Photo photo = unrankedPhotos.poll();
		notifyAll();
		return photo;
	}

	public synchronized void putUnrankedPhotos(Collection<Photo> photos) {
		unrankedPhotos.addAll(photos);
		notifyAll();
	}

	public synchronized void putRankedPhoto(RankedPhoto photo) {
		unrankedOut--;
		rankedPhotos.add(photo);
		notifyAll();
	}

	public synchronized boolean hasUnrankedPhotos() {
		if (producingUnrankedPhotos) {
			while (unrankedPhotos.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println("Something get wrong");
				}
			}
		}

		return producingUnrankedPhotos || !unrankedPhotos.isEmpty();
	}

	public synchronized void noMoreUnrankedPhotos() {
		producingUnrankedPhotos = false;
	}

	public Collection<RankedPhoto> getRankedPhotos() {
		return rankedPhotos;
	}

}
