package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.util.JsonUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  // HTTP status codes
  private static final int HTTP_OK = 200;
  private static final int HTTP_UNAUTHORIZED = 401;
  private static final int HTTP_FORBIDDEN = 403;
  private static final int HTTP_NOT_FOUND = 404;
  private final String IEX_BATCH_URL;
  private final HttpClientConnectionManager httpClientConnectionManager;

  private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    logger.debug("MarketDataDao JDBC connection created.");
  }

  /**
   * Get an IexQuote.
   *
   * @param ticker
   * @return
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      logger.error("MarketDataDao findById() caught exception.");
      throw new DataRetrievalFailureException("Unexpected number of quotes.");
    }
    return iexQuote;
  }

  /**
   * Get quotes from IEX.
   *
   * @param tickers is a list of tickers
   * @return a list of IexQuote objects
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<IexQuote> iexQuotes = new ArrayList<>();
    String tickerString = String.join(",", tickers);

    String uri = String.format(IEX_BATCH_URL, tickerString);
    String responseString = executeHttpGet(uri).get();
    // Deserialize JSON String to IexQuote object
    JSONObject jsonQuotes = new JSONObject(responseString);

    for (String ticker : tickers) {
      if (jsonQuotes.has(ticker)) {
        JSONObject jsonQuote = jsonQuotes.getJSONObject(ticker);
        try {
          iexQuotes.add(JsonUtil.toObjectFromJson(jsonQuote.get("quote").toString(),
              IexQuote.class));
        } catch (IOException e) {
          logger.error(
              "MarketDataDao findAllById() caught exception (failed to deserialize JSON).");
          throw new RuntimeException("Failed to deserialize JSON for ticker: " + ticker);
        }
      } else {
        logger.error("MarketDataDao findAllById() caught exception (parsed unknown ticker).");
        throw new RuntimeException("Unknown ticker in response: " + ticker);
      }
    }
    return iexQuotes;
  }

  /**
   * Execute a GET and return body as a String.
   *
   * @param uri resource URI
   * @return Response body or Optional.empty() if a 404 response is returned
   */
  private Optional<String> executeHttpGet(String uri) {
    HttpClient httpClient = getHttpClient();
    HttpGet request = new HttpGet(uri);
    HttpResponse response;
    Optional<String> responseQuotes;

    try {
      response = httpClient.execute(request);
    } catch (IOException e) {
      logger.error("MarketDataDao executeHttpGet() caught exception (failed to execute).");
      throw new RuntimeException("Failed to execute GET request.", e);
    }

    int status = response.getStatusLine().getStatusCode();
    switch (status) {
      case HTTP_OK:
        try {
          responseQuotes = Optional.of(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
          logger.error(
              "MarketDataDao executeHttpGet() caught exception (failed String conversion).");
          throw new RuntimeException("Failed to convert response to String.", e);
        }
        break;
      case HTTP_UNAUTHORIZED:
        logger.error("MarketDataDao executeHttpGet() caught exception (401 status code).");
        throw new RuntimeException("HTTP response: Unauthorized.");
      case HTTP_FORBIDDEN:
        logger.error("MarketDataDao executeHttpGet() caught exception (403 status code).");
        throw new RuntimeException("HTTP response: Forbidden.");
      case HTTP_NOT_FOUND:
        logger.debug("MarketDataDao executeHttpGet() retrieved 404 status code.");
        return Optional.empty();
      default:
        throw new RuntimeException("GET request HTTP response: " + status);
    }

    return responseQuotes;
  }

  /**
   * Borrow an HTTP client from the connection manager.
   *
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        // Prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true)
        .build();
  }

  // Unimplemented methods

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }
}
