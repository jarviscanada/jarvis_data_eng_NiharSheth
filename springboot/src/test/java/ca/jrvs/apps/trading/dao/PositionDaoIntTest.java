package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import org.junit.After;
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
public class PositionDaoIntTest {

  @Autowired
  private PositionDao positionDao;

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private QuoteDao quoteDao;

  private Position savedPosition;
  private SecurityOrder savedSecurityOrder;
  private Trader savedTrader;
  private Account savedAccount;
  private Quote savedQuote;

  @Before
  public void insertOne() {
    // Save a trader
    savedTrader = new Trader();
    savedTrader.setId(1);
    savedTrader.setFirstName("Nihar");
    savedTrader.setLastName("Sheth");
    savedTrader.setDob(Date.valueOf("1998-04-16"));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("niharsheth98@gmail.com");
    traderDao.save(savedTrader);

    // Save an account
    savedAccount = new Account();
    savedAccount.setId(1);
    savedAccount.setTraderId(1);
    savedAccount.setAmount(20.8f);
    accountDao.save(savedAccount);

    // Save a quote
    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("TSLA");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);

    // Save a security order
    savedSecurityOrder = new SecurityOrder();
    savedSecurityOrder.setId(1);
    savedSecurityOrder.setAccountId(1);
    savedSecurityOrder.setStatus("Open");
    savedSecurityOrder.setTicker("TSLA");
    savedSecurityOrder.setSize(10);
    savedSecurityOrder.setPrice(11.5f);
    savedSecurityOrder.setNotes("Test notes.");
    securityOrderDao.save(savedSecurityOrder);

    /*savedPosition = new Position();
    savedPosition.setAccountId(1);
    savedPosition.setTicker("TSLA");
    savedPosition.setPosition(10);*/
  }

  @After
  public void tearDown() {
    securityOrderDao.deleteById(savedSecurityOrder.getId());
    quoteDao.deleteById(savedQuote.getId());
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void count() {
    assertEquals(1, positionDao.count());
  }
}