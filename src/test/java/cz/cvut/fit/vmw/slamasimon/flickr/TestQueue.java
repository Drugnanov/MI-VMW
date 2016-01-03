package cz.cvut.fit.vmw.slamasimon.flickr;

import cz.cvut.fit.vmw.slamasimon.flickr.controller.model.SearchData;
import cz.cvut.fit.vmw.slamasimon.flickr.model.RankedPhoto;
import cz.cvut.fit.vmw.slamasimon.flickr.service.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Drugnanov on 19.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class TestQueue extends AbstractJUnit4SpringContextTests {

  protected ApplicationContext appContext;

  @Test
  public void simple() throws Exception {
    PhotoService photoService = (PhotoService) applicationContext.getBean("photoService");
    SearchData sd = new SearchData();
//    sd.setTag("dog");
//    sd.setMaxNumberOfPhotos(5);
    List<RankedPhoto> photoList = photoService.search(sd);
    System.out.println("test");
    for (RankedPhoto photoRanked : photoList) {
      System.out.println(photoRanked.photo.getTitle());
    }
  }
}
