package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

public class MarketDataDaoIntTest {

  private MarketDataDao marketDataDao;

  @Before
  public void setUp() throws Exception {
    // Set up connection manager
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);

    // Set up marketDataConfig
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

    marketDataDao = new MarketDataDao(cm, marketDataConfig);
  }

  @Test
  public void findIexQuotesByTickers() throws IOException {
    // Valid query
    List<IexQuote> iexQuotes = marketDataDao.findAllById(Arrays.asList("AAPL", "MSFT"));
    assertEquals(2, iexQuotes.size());
    assertEquals("AAPL", iexQuotes.get(0).getSymbol());

    // Invalid query
    try {
      marketDataDao.findAllById(Arrays.asList("AAPL", "MSFT2"));
      fail();
    } catch (Exception e) {
      assertTrue(true);
    }
  }

  @Test
  public void findByTicker() {
    String ticker = "AAPL";
    IexQuote iexQuote = marketDataDao.findById(ticker).get();
    assertEquals(ticker, iexQuote.getSymbol());
  }
}
