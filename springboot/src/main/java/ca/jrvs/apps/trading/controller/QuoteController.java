package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/quote")
public class QuoteController {

  private final QuoteService quoteService;
  private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

  @Autowired
  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping(path = "iex/ticker/{ticker}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public IexQuote getQuote(@PathVariable String ticker) {
    try {
      return quoteService.findIexQuoteByTicker(ticker);
    } catch (Exception e) {
      logger.error("QuoteController getQuote() caught exception.");
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @PutMapping(path = "/")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Quote putQuote(@RequestBody Quote quote) {
    try {
      return quoteService.saveQuote(quote);
    } catch (Exception e) {
      logger.error("QuoteController putQuote() caught exception.");
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @PostMapping(path = "/tickerId/{tickerId}")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Quote createQuote(@PathVariable String tickerId) {
    try {
      return quoteService.saveQuote(tickerId);
    } catch (Exception e) {
      logger.error("QuoteController createQuote() caught exception.");
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @PutMapping(path = "/iexMarketData")
  @ResponseStatus(HttpStatus.OK)
  public void updateMarketData() {
    try {
      quoteService.updateMarketData();
    } catch (Exception e) {
      logger.error("QuoteController updateMarketData() caught exception.");
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @GetMapping(path = "/dailyList")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<Quote> getDailyList() {
    try {
      return quoteService.findAllQuotes();
    } catch (Exception e) {
      logger.error("QuoteController getDailyList() caught exception.");
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
}
