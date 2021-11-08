package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterDao implements CrdDao <Tweet, String> {

  // URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  // URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  // Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;
  private PercentEscaper percentEscaper = new PercentEscaper("", false);

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  public URI getPostUri(Tweet tweet) {
    URI uri = null;

    String status = percentEscaper.escape(tweet.getText());
    Coordinates coordinates = tweet.getCoordinates();
    float longitude = coordinates.getCoordinates()[0];
    float latitude = coordinates.getCoordinates()[1];

    String uriStr = API_BASE_URI + POST_PATH + QUERY_SYM
        + "status" + EQUAL + status + AMPERSAND
        + "lat" + EQUAL + latitude + AMPERSAND
        + "long" + EQUAL + longitude;

    System.out.println(uriStr);

    try {
      uri = new URI(uriStr);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Unable to create post URI.", e);
    }

    return uri;
  }

  public URI getShowUri(String idStr) {
    URI uri = null;

    String uriStr = API_BASE_URI + SHOW_PATH + QUERY_SYM
        + "id" + EQUAL + idStr;

    try {
      uri = new URI(uriStr);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Unable to create show URI.", e);
    }

    return uri;
  }

  public URI getDeleteUri(String idStr) {
    URI uri = null;

    String uriStr = API_BASE_URI + DELETE_PATH +
        idStr + ".json";

    try {
      uri = new URI(uriStr);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Unable to create delete URI.", e);
    }

    return uri;
  }

  @Override
  public Tweet create(Tweet tweet) {
    URI uri;

    try {
      uri = getPostUri(tweet);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid Tweet input.", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String id) {
    URI uri;

    try {
      uri = getShowUri(id);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID String input.", e);
    }

    HttpResponse response = httpHelper.httpGet(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String s) {
    URI uri;

    try {
      uri = getDeleteUri(s);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid ID String input.", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  private Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    int status = response.getStatusLine().getStatusCode();

    if(status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        // TODO: error log
      }
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }

    if(response.getEntity() == null) {
      throw new RuntimeException("Empty response body.");
    }

    // Convert the response to String
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String.", e);
    }
    System.out.println(jsonStr);
    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON to Object.", e);
    }

    return tweet;
  }
}
