package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setUp() {
    quoteDao.deleteAll();
  }

  @Test
  public void saveQuote() {
    quoteService.saveQuote("AAPL");
    assertEquals("AAPL", quoteDao.findById("AAPL").get().getId());
  }

  @Test
  public void saveQuotes() {
    List<String> tickers = new ArrayList<>();
    tickers.add("AAPL");
    tickers.add("TSLA");

    quoteService.saveQuotes(tickers);
    assertEquals("AAPL", quoteDao.findById("AAPL").get().getId());
    assertEquals("TSLA", quoteDao.findById("TSLA").get().getId());
  }

  @Test
  public void findIexQuotesByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("AAPL");
    assertEquals("AAPL", iexQuote.getSymbol());
  }

  @Test
  public void findAllQuotes() {
    quoteService.saveQuote("AAPL");
    quoteService.saveQuote("TSLA");
    List<Quote> foundQuotes = quoteService.findAllQuotes();
    assertEquals("AAPL", foundQuotes.get(0).getId());
    assertEquals("TSLA", foundQuotes.get(1).getId());
  }

  @Test
  public void updateMarketData() {
    Quote wrongQuote = new Quote();
    wrongQuote.setAskPrice(10d);
    wrongQuote.setAskSize(10);
    wrongQuote.setBidPrice(10.2d);
    wrongQuote.setBidSize(10);
    wrongQuote.setId("AAPL");
    wrongQuote.setLastPrice(10.1d);
    quoteDao.save(wrongQuote);
    quoteService.updateMarketData();
    assertNotEquals(wrongQuote, quoteDao.findById("AAPL").get());
  }
}