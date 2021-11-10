package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "ca.jrvs.apps.twitter")
public class TwitterCLIComponentScan {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        TwitterCLIComponentScan.class);
    TwitterCLIApp twitterCLIApp = context.getBean(TwitterCLIApp.class);
    try {
      twitterCLIApp.run(args);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to serialize Tweet object to JSON.", e);
    }
  }
}
