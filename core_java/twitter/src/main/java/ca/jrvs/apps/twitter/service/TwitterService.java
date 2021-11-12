package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private final CrdDao dao;
  private final Logger logger = LoggerFactory.getLogger(TwitterService.class);

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    logger.debug("POST request validated.");
    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateID(id);
    logger.debug("SHOW request validated for ID", id);
    return (Tweet) dao.findById(id);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> deletedTweets = new ArrayList<>();

    for (String id : ids) {
      validateID(id);
      logger.debug("DELETE request validated for ID", id);

      deletedTweets.add((Tweet) dao.deleteById(id));
    }
    return deletedTweets;
  }

  /**
   * Business logic: Tweet text length should be 280 characters or fewer and latitude/longitude
   * values are within range.
   *
   * @param tweet Tweet to validate
   * @return True if tweet passes all checks.
   */
  public boolean validatePostTweet(Tweet tweet) {
    String text = tweet.getText();
    if (text.length() > 280) {
      logger.error("User passed status > 280 characters.");
      throw new IllegalArgumentException("Tweet status must be 280 characters or fewer.");
    }

    // Latitude: -90 to +90, longitude: -180 to +180
    float[] coordinates = tweet.getCoordinates().getCoordinates();
    float longitude = coordinates[0];
    float latitude = coordinates[1];

    if (longitude < -180 || longitude > 180) {
      logger.error("User passed longitude outside range.");
      throw new IllegalArgumentException("Longitude must be within -180 to +180 degrees.");
    }

    if (latitude < -90 || latitude > 90) {
      logger.error("User passed latitude outside range.");
      throw new IllegalArgumentException("Latitude must be within -90 to +90 degrees.");
    }

    return true;
  }

  /**
   * Business logic: ID should be in a valid format.
   *
   * @param id Tweet ID
   * @return
   */
  public boolean validateID(String id) {
    if (id.length() > 19) {
      logger.error("User passed invalid ID.");
      throw new IllegalArgumentException("Passed ID is not valid.");
    }

    return true;
  }
}
