package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";
  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  @Override
  public <S extends Quote> S save(S quote) {
    if (existsById(quote.getId())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote.");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * Helper method that saves one quote.
   *
   * @param quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    //int row = simpleJdbcInsert.execute(parameterSource);
    Map<String, Object> insertMap = new HashMap<>();
    insertMap.put("ticker", quote.getId());
    insertMap.put("last_price", quote.getLastPrice());
    insertMap.put("bid_price", quote.getBidPrice());
    insertMap.put("bid_size", quote.getBidSize());
    insertMap.put("ask_price", quote.getAskPrice());
    insertMap.put("ask_size", quote.getAskSize());
    int row = simpleJdbcInsert.execute(insertMap);
    if (row != 1) {
      throw new RuntimeException();
    }
  }

  /**
   * Helper method that updates one quote.
   *
   * @param quote
   * @return
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * Helper method that makes SQL update values objects.
   *
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote) {
    Object[] objects = new Object[]{
        quote.getLastPrice(),
        quote.getBidPrice(),
        quote.getBidSize(),
        quote.getAskPrice(),
        quote.getAskSize(),
        quote.getId()
    };

    return objects;
  }

  public <S extends Quote> List<S> saveAll(Iterable<S> quotes) {
    List<S> savedQuotes = new ArrayList<>();
    for (S quote : quotes) {
      savedQuotes.add(save(quote));
    }
    return savedQuotes;
  }

  /**
   * Returns all quotes.
   *
   * @return
   */
  @Override
  public List<Quote> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<Quote> foundQuotes = jdbcTemplate.query(selectSql,
        BeanPropertyRowMapper.newInstance(Quote.class));
    return foundQuotes;
  }

  /**
   * Find a quote by ticker.
   *
   * @param ticker name
   * @return quote or Optional.empty if not found
   */
  @Override
  public Optional<Quote> findById(String ticker) {
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "='" + ticker + "'";
    Optional<Quote> foundQuote = Optional.empty();
    try {
      foundQuote = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Quote.class)));
    } catch(EmptyResultDataAccessException e) {

    }

    if (foundQuote.isPresent()) {
      return foundQuote;
    }

    return Optional.empty();
  }

  /**
   * Check if a quote exists in the table
   *
   * @param ticker of quote to check for
   * @return true if quote exists
   */
  @Override
  public boolean existsById(String ticker) {
    return findById(ticker).isPresent();
  }

  /**
   * Delete quote by ID.
   *
   * @param ticker of quote to delete
   */
  @Override
  public void deleteById(String ticker) {
    if (ticker == null) {
      throw new IllegalArgumentException("ID cannot be null.");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
    jdbcTemplate.update(deleteSql, ticker);
  }

  /**
   * Get count of total quotes in table.
   *
   * @return count
   */
  @Override
  public long count() {
    String countSql = "SELECT count(*) FROM " + TABLE_NAME;
    return jdbcTemplate.queryForObject(countSql, Long.class);
  }

  /**
   * Delete all quotes.
   */
  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }

  // Unimplemented methods

  @Override
  public Iterable<Quote> findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }
}
