package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
  private final QuoteDao quoteDao;
  private final MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Helper method for mapping an IexQuote to a Quote entity. Note: `iexQuote.getLatestPrice() ==
   * null` if the stock market is closed.
   *
   * @param iexQuote
   * @return
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote();
    quote.setId(iexQuote.getSymbol());
    quote.setLastPrice(iexQuote.getLatestPrice());
    quote.setBidPrice(Double.valueOf(iexQuote.getIexBidPrice()));
    quote.setBidSize(iexQuote.getIexBidSize());
    quote.setAskPrice(iexQuote.getIexAskPrice());
    quote.setAskSize(iexQuote.getIexAskSize());

    return quote;
  }

  /**
   * Update quote table against IEX source - Get all quotes from the database - For each ticker get
   * IexQuote - Convert IexQuote to Quote entity - Persist Quote to database
   */
  public void updateMarketData() {
    List<Quote> quotes = findAllQuotes();
    List<String> tickers = quotes.stream().map(quote -> quote.getTicker())
        .collect(Collectors.toList());
    saveQuotes(tickers);
  }

  /**
   * Validate (against IEX) and save given tickers to quote table. - Get IexQuote(s) - Convert each
   * IexQuote to Quote entity - Persist the Quote to the database
   *
   * @param tickers a list of tickers/symbols
   * @return
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<Quote> quotes = new ArrayList<>();
    for (String ticker : tickers) {
      quotes.add(quoteDao.save(saveQuote(ticker)));
    }

    return quotes;
  }

  /**
   * Find an IexQuote.
   *
   * @param ticker id
   * @return IexQuote object
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker."));
  }

  /**
   * Update a given Quote to Quote table without validation.
   *
   * @param quote entity
   * @return
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  public Quote saveQuote(String ticker) {
    Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
    return quoteDao.save(buildQuoteFromIexQuote(
        iexQuote.orElseThrow(() -> new IllegalArgumentException("Invalid ticker."))));
  }

  /**
   * Find all Quotes from the Quote table.
   *
   * @return a list of Quotes
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}
