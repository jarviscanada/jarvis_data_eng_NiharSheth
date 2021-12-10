package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.List;
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
public class TraderDaoIntTest {

  @Autowired
  private TraderDao traderDao;

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
  }

  @After
  public void deleteOne() {
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void count() {
    assertEquals(1, traderDao.count());
  }

  @Test
  public void findById() {
    assertEquals(savedTrader, traderDao.findById(1).get());
  }

  @Test
  public void existsById() {
    assertTrue(traderDao.existsById(1));
  }

  @Test
  public void findAll() {
    Trader anotherTrader = new Trader();
    anotherTrader.setId(2);
    anotherTrader.setFirstName("Another");
    anotherTrader.setLastName("Trader");
    anotherTrader.setDob(Date.valueOf("1992-09-22"));
    anotherTrader.setCountry("Canada");
    anotherTrader.setEmail("trader@gmail.com");
    traderDao.save(anotherTrader);

    List<Trader> foundTraders = traderDao.findAll();

    assertEquals(2, foundTraders.size());
    assertEquals(savedTrader.getCountry(), foundTraders.get(0).getCountry());
  }
}
