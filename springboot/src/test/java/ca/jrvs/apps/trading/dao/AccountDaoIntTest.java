package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  private Account savedAccount;
  private Trader savedTrader;

  @Before
  public void insertOne() {
    savedTrader = new Trader();
    savedTrader.setId(1);
    savedTrader.setFirstName("Nihar");
    savedTrader.setLastName("Sheth");
    savedTrader.setDob(Date.valueOf("1998-04-16"));
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("niharsheth98@gmail.com");
    traderDao.save(savedTrader);

    savedAccount = new Account();
    savedAccount.setId(1);
    savedAccount.setTraderId(1);
    savedAccount.setAmount(20.8f);
    accountDao.save(savedAccount);
  }

  @After
  public void deleteOne() {
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void count() {
    assertEquals(1, accountDao.count());
  }

  @Test
  public void findById() {
    assertEquals(savedAccount, accountDao.findById(1).get());
  }

  @Test
  public void existsById() {
    assertTrue(accountDao.existsById(1));
  }

  @Test
  public void findAll() {
    Account anotherAccount = new Account();
    anotherAccount.setId(2);
    anotherAccount.setTraderId(1);
    anotherAccount.setAmount(15.2f);
    accountDao.save(anotherAccount);

    List<Account> foundAccounts = accountDao.findAll();

    assertEquals(2, foundAccounts.size());
    assertEquals(savedAccount.getId(), foundAccounts.get(0).getId());
    assertEquals(savedAccount.getTraderId(), foundAccounts.get(1).getTraderId());

    accountDao.deleteById(2);
  }

  @Test
  public void findByTraderId() {
    assertEquals(savedAccount, accountDao.findByTraderId(1));
  }
}