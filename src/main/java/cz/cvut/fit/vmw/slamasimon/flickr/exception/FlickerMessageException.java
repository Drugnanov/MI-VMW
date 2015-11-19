package cz.cvut.fit.vmw.slamasimon.flickr.exception;

/**
 * Created by Drugnanov on 18.11.2015.
 */
public class FlickerMessageException extends Exception {
  String messageId;
  Object[] parameters;
  public FlickerMessageException(String messageId, Object[] parameters) {
    this.messageId = messageId;
    this.parameters = parameters;
  }
}
