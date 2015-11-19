package cz.cvut.fit.vmw.slamasimon.flickr;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.google.common.collect.SortedMultiset;
import cz.cvut.fit.vmw.slamasimon.flickr.model.PhotoRanked;
import cz.cvut.fit.vmw.slamasimon.flickr.service.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    SortedMultiset<PhotoRanked> photoList = photoService.search("dog", 5);
    System.out.println("test");
    for (PhotoRanked photoRanked : photoList) {
      System.out.println(photoRanked.photo.getTitle());
    }
  }
}
