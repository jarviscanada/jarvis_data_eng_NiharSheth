package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

  private final TraderDao traderDao;
  private final AccountDao accountDao;
  private final PositionDao positionDao;
  private final SecurityOrderDao securityOrderDao;
  private Account account;
  private static final Logger logger = LoggerFactory.getLogger(TraderAccountService.class);

  @Autowired
  public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao,
      SecurityOrderDao securityOrderDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.securityOrderDao = securityOrderDao;
    logger.debug("TraderAccountService JDBC connection created.");
  }

  public TraderAccountView createTraderAndAccount(Trader trader) {
    account = new Account();
    account.setTraderId(trader.getId());
    account.setAmount(0f);

    TraderAccountView traderAccountView = new TraderAccountView();
    traderAccountView.setTrader(traderDao.save(trader));
    traderAccountView.setAccount(accountDao.save(account));
    return traderAccountView;
  }

  public void deleteTraderById(Integer traderId) {
    account = accountDao.findByTraderId(traderId);
    if (account.getAmount().equals(0f)) {
      securityOrderDao.deleteById(traderId);
      accountDao.deleteById(traderId);
      traderDao.deleteById(traderId);
    } else {
      logger.error("TraderAccountService deleteTraderById() caught exception.");
      throw new RuntimeException(
          "A trader can be deleted iff it has no open position and 0 cash balance.");
    }
  }

  public Account deposit(Integer traderId, Float fund) {
    if (fund < 0) {
      logger.error("TraderAccountService deposit() caught exception.");
      throw new IllegalArgumentException("Fund must be a positive value.");
    }
    account = accountDao.findByTraderId(traderId);
    account.setAmount(account.getAmount() + fund);
    accountDao.updateOne(account);
    return account;
  }

  public Account withdraw(Integer traderId, Float fund) {
    if (fund < 0) {
      logger.error("TraderAccountService withdraw() caught exception (negative fund passed).");
      throw new IllegalArgumentException("Fund must be a positive value.");
    }

    account = accountDao.findByTraderId(traderId);
    if (fund > account.getAmount()) {
      logger.error(
          "TraderAccountService withdraw() caught exception (fund passed with value greater than account amount).");
      throw new IllegalArgumentException(
          "Cannot withdraw a value higher than the existing account amount.");
    }

    account.setAmount(account.getAmount() - fund);
    accountDao.updateOne(account);
    return account;
  }
}
