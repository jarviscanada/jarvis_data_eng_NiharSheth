package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  private Logger logger = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public MarketDataConfig marketDataConfig() {
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
    return marketDataConfig;
  }

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

  @Bean
  public HttpClientConnectionManager httpClientConnectionManager() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    return cm;
  }
}
