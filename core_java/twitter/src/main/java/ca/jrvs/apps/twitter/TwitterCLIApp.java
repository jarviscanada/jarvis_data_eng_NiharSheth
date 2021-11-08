package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterCLIApp {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");

  public static void main(String[] args) throws URISyntaxException, IOException {
    TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    PercentEscaper percentEscaper = new PercentEscaper("", false);

    String verb = null;
    String status = null;
    String geotag = null;
    String id = null;

    if(args.length == 3) {
      verb = args[0].toLowerCase();
      status = args[1];
      geotag = args[2];
    }
    else if(args.length == 2) {
      verb = args[0].toLowerCase();
      id = args[1];
    }
    else {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    }

    switch (verb) {
      case "post":
        // TODO
        helper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status)));
        break;
      case "show":
        // TODO
        HttpResponse response = helper.httpGet((new URI("https://api.twitter.com/2/tweets/" + id)));
        System.out.println(EntityUtils.toString(response.getEntity()));
        break;
      case "delete":
        // TODO
        break;
      default:
        throw new IllegalArgumentException("Unknown HTTP verb.");
    }
  }
}
