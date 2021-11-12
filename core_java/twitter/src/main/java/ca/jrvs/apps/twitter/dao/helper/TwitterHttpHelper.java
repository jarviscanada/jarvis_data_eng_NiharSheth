package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  private final OAuthConsumer consumer;
  private final HttpClient httpClient;
  private final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

  /**
   * Set up dependencies with secrets.
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {
    //Setup OAuth
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    httpClient = new DefaultHttpClient();
    logger.debug("HTTP client created with secrets.");
  }

  /**
   * Default constructor
   */
  public TwitterHttpHelper() {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
    httpClient = new DefaultHttpClient();
    logger.debug("HTTP client created with secrets. (Default constructor)");
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    HttpPost request = new HttpPost(uri);

    try {
      consumer.sign(request);
      logger.debug("POST request signed and executed.");
      return httpClient.execute(request);
    } catch (Exception e) {
      logger.error("Exception caught during runtime.");
      throw new RuntimeException("Failed to execute POST request.", e);
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    HttpGet request = new HttpGet(uri);

    try {
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (Exception e) {
      logger.error("Exception caught during runtime.");
      throw new RuntimeException("Failed to execute GET request.", e);
    }
  }
}
