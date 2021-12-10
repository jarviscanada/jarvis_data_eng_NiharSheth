package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class,
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class})
public class Application implements CommandLineRunner {

  @Autowired
  private QuoteService quoteService;

  public static void main(String[] args) throws Exception {
    Logger logger = LoggerFactory.getLogger(Application.class);
    SpringApplication app = new SpringApplication(Application.class);
    logger.debug("Application IoC container instantiated.");
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
