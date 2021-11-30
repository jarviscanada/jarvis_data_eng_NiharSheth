package ca.jrvs.apps.trading.AppConfig;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;

public class AppConfig {
  @Bean
  public DataSource dataSource() {
    String jdbcUrl = "jdbc:postgresql://" +
        System.getenv("PSQL_HOST") + ":" +
        System.getenv("PSQL_PORT") +
        "/" +
        System.getenv("PSQL_DB");
    String user = System.getenv("PSQL_USER");
    String password = System.getenv("PSQL_PASSWORD");

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(jdbcUrl);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);

    return basicDataSource;
  }
}
