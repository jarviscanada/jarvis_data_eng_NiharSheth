package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao twitterDao;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN,
        TOKEN_SECRET);
    this.twitterDao = new TwitterDao(httpHelper);
  }

  //@Test
  public void create() {
    Tweet post = new Tweet();

    // Tweet setup
    String status = "@Canada TwitterDao integration test9. ";
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

    Tweet create = twitterDao.create(post);

    try {
      System.out.println("CREATE RESPONSE:\n" + JsonUtil.toJson(create, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert response to JSON.", e);
    }
  }

  //@Test
  public void findById() {
    String idStr = "1433935396094693378";
    Tweet findById = twitterDao.findById(idStr);
    try {
      System.out.println("FOUND TWEET:\n" + JsonUtil.toJson(findById, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert retrieved Tweet to JSON", e);
    }
  }

  //@Test
  public void deleteById() {
    String idStr = "1457762011614486529";
    Tweet deleteById = twitterDao.deleteById(idStr);
    try {
      System.out.println("DELETED TWEET:\n" + JsonUtil.toJson(deleteById, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert deleted Tweet to JSON", e);
    }
  }
}