package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {
  private CrdDao dao;
  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN,
        TOKEN_SECRET);

    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }

  //@Test
  public void postTweet() {
    // Setup Tweet to post
    Tweet postTweet = new Tweet();
    postTweet.setText("Service layer integration testing postTweet()");
    float[] coords = new float[] {43, -79};
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(coords);
    postTweet.setCoordinates(coordinates);

    Tweet postedTweet = service.postTweet(postTweet);
  }

  //@Test
  public void showTweet() {
    String showId = "1433935396094693378";

    Tweet showedTweet = service.showTweet(showId, null);
  }

  //@Test
  public void deleteTweets() {
    String deleteId = "1458106966627205122";
    String [] ids = new String[] {deleteId};
    List<Tweet> deletedTweets = service.deleteTweets(ids);
  }
}