package cz.cvut.fit.vmw.slamasimon.flickr.exception;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class FlickerException extends Exception {
  public FlickerException(String text, Throwable ex) {
    super(text, ex);
  }
}
