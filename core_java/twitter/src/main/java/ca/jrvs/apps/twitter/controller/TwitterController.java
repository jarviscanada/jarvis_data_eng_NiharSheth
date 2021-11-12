package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private final Service service;
  private final Logger logger = LoggerFactory.getLogger(TwitterController.class);

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      logger.error("User passed incorrect number of arguments for POST request.");
      throw new IllegalArgumentException(
          "USAGE: TwitterApp \"post\" \"text\" \"latitude:longitude\"");
    }

    String text = args[1];
    String coordinatesStr = args[2];

    String[] lat_long;
    try {
      lat_long = coordinatesStr.split(COORD_SEP);
    } catch (Exception e) {
      logger.error("User passed invalid location String.");
      throw new IllegalArgumentException("Incorrect location format: latitude:longitude");
    }

    float latitude, longitude;
    try {
      latitude = Float.parseFloat(lat_long[0]);
      longitude = Float.parseFloat(lat_long[1]);
    } catch (NumberFormatException e) {
      logger.error("User passed invalid latitude/longitude number values.");
      throw new IllegalArgumentException("Incorrect latitude/longitude format: latitude:longitude",
          e);
    }

    Tweet post = new Tweet();
    post.setText(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new float[]{longitude, latitude});
    coordinates.setType("Point");
    post.setCoordinates(coordinates);

    return service.postTweet(post);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 2) {
      logger.error("User passed incorrect number of arguments for SHOW request.");
      throw new IllegalArgumentException(
          "USAGE: TwitterApp \"show\" \"tweet_id\" \"[field1,field2]\"");
    }

    String id = args[1];

    return service.showTweet(id, null);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length < 2) {
      logger.error("User passed incorrect number of arguments for DELETE request.");
      throw new IllegalArgumentException(
          "USAGE: TwitterApp \"delete\" \"[id1, id2,...]\"");
    }

    String[] ids = args[1].split(COMMA);

    return service.deleteTweets(ids);
  }
}
