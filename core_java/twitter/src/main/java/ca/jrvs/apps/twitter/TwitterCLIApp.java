package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.JsonUtil;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class TwitterCLIApp {

  private final Controller controller;

  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  public static void main(String[] args) {
    // Declare and instantiate components
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN,
        TOKEN_SECRET);
    CrdDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);

    TwitterCLIApp cliApp = new TwitterCLIApp(controller);

    // TODO: Call run() method
    try {
      cliApp.run(args);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to serialize Tweet object to JSON.", e);
    }
  }

  /**
   * Passed arguments are parsed and controller methods are called, results are printed.
   *
   * @param args Arguments to pass such as request type and corresponding fields.
   */
  public void run(String[] args) throws JsonProcessingException {
    if (args.length < 2) {
      throw new IllegalArgumentException("Incorrect number of arguments.\n"
          + "\tPost tweet - USAGE: TwitterApp \"post\" \"text\" \"latitude:longitude\"\n"
          + "\tShow tweet - USAGE: TwitterApp \"show\" \"tweet_id\" \"[field1,field2]\"\n"
          + "\tDelete tweet(s) - USAGE: TwitterApp \"delete\" \"[id1, id2,...]\"");
    }

    String verb = args[0].toLowerCase();

    switch (verb) {
      case "post":
        Tweet postedTweet = controller.postTweet(args);
        printedTweet(postedTweet);
        break;
      case "show":
        Tweet showedTweet = controller.showTweet(args);
        printedTweet(showedTweet);
        break;
      case "delete":
        List<Tweet> deletedTweets = controller.deleteTweet(args);
        for (Tweet deletedTweet : deletedTweets) {
          printedTweet(deletedTweet);
        }
        break;
      default:
        throw new IllegalArgumentException("Unknown request type.");
    }
  }

  /**
   * Utility to print the JSON format of a serialized Tweet object.
   *
   * @param tweet Tweet to serialize and print.
   */
  public void printedTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtil.toJson(tweet, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert Tweet to JSON string.", e);
    }
  }
}
