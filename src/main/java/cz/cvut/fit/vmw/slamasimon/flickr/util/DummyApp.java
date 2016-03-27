package cz.cvut.fit.vmw.slamasimon.flickr.util;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.comments.Comment;
import cz.cvut.fit.vmw.slamasimon.flickr.model.CommentedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.service.GateService;
import gate.util.GateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon.
 */
public class DummyApp
{
  public static void main(String[] args)
  {
    GateService gs = new GateService("/home/simon/Apps/GATE_Developer_8.1",
            "/home/simon/Apps/GATE_Developer_8.1/plugins",
            "/mnt/DATA/Dropbox/MGR/1.semestr/VMW/MI-VMW/src/main/resources/grammar.jape");
    List<CommentedPhoto> photos = new ArrayList<CommentedPhoto>();
    Photo photo = new Photo();
    List<Comment> comments = new ArrayList<Comment>();
    CommentedPhoto commentedPhoto = new CommentedPhoto(photo, comments);
    photos.add(commentedPhoto);

    try {
      System.out.println("Result: " + gs.computeSentiment(photos));
    } catch (GateException e) {
      System.out.println("Something went wrong");
      e.printStackTrace();
    }
  }
}
