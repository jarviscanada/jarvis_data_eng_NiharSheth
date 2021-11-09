package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private Controller controller;
  private Service service;
  private CrdDao dao;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN,
        TOKEN_SECRET);

    dao = new TwitterDao(httpHelper);
    service = new TwitterService(dao);
    controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    String[] args = new String[] {"post", "Controller layer integration testing.", "43:-79"};
    Tweet postedTweet = controller.postTweet(args);
  }

  @Test
  public void showTweet() {
    String[] args = new String[] {"show", "1433935396094693378"};
    Tweet showedTweet = controller.showTweet(args);
  }

  @Test
  public void deleteTweet() {
    String[] args = new String[] {"delete", "1458186722777387018,1458186741064503296,1458186760148635655"};
    List<Tweet> deletedTweets = controller.deleteTweet(args);
  }
}
