package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  //@Test
  public void create() {
    Tweet post = new Tweet();

    // Tweet setup
    String status = "@Canada TwitterDao integration test10. ";
    String hashtag = "#DAO #Integration";
    float latitude = 43;
    float longitude = -79;
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new float[]{longitude, latitude});
    coordinates.setType("Point"); // required

    post.setText(status + hashtag);
    post.setCoordinates(coordinates);

    try {
      System.out.println("TWEET TO POST:\n" + JsonUtil.toJson(post, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert Tweet to JSON.", e);
    }

    Tweet create = dao.create(post);

    try {
      System.out.println("CREATE RESPONSE:\n" + JsonUtil.toJson(create, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert response to JSON.", e);
    }
  }
}
