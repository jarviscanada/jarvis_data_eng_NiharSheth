package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
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
public class TraderAccountServiceIntTest {

  @Autowired
  private TraderAccountService traderAccountService;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private AccountDao accountDao;

  private TraderAccountView savedTraderAccountView;
  private Trader savedTrader;
  private Account savedAccount;

  @Before
  public void setUp() {
    // Save trader
    savedTrader = new Trader();
    savedTrader.setId(1);
    savedTrader.setFirstName("Nihar");
    savedTrader.setLastName("Sheth");
    savedTrader.setDob(Date.valueOf("1998-04-16"));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("niharsheth98@gmail.com");

    // Create new account for the trader
    savedTraderAccountView = traderAccountService.createTraderAndAccount(savedTrader);
  }

  @After
  public void tearDown() {
    traderAccountService.deleteTraderById(1);
  }

  @Test
  public void newAccountExists() {
    Float amount = accountDao.findByTraderId(1).getAmount();
    assertEquals(0, amount, 0);
  }

  @Test
  public void depositAndWithDraw() {
    // Deposit into the account
    traderAccountService.deposit(1, 10f);
    Float amount = accountDao.findByTraderId(1).getAmount();
    assertEquals(10, amount, 0);

    // Withdraw from the account, leaving a balance of 0 so the account and its trader can be deleted
    traderAccountService.withdraw(1, 10f);
    amount = accountDao.findByTraderId(1).getAmount();
    assertEquals(0, amount, 0);
  }
}
