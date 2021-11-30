package ca.jrvs.apps.trading.model.config;

public class MarketDataConfig {

  private String host, token;

  /**
   * Default constructor.
   */
  public MarketDataConfig() {

  }

  public MarketDataConfig(String host, String token) {
    this.host = host;
    this.token = token;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
