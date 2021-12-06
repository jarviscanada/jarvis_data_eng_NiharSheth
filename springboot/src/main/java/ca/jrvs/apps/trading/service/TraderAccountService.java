package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {
  private TraderDao traderDao;
  private AccountDao accountDao;
  private PositionDao positionDao;
  private SecurityOrderDao securityOrderDao;
  private Account account;

  @Autowired
  public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao, SecurityOrderDao securityOrderDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.securityOrderDao = securityOrderDao;
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
    if(account.getAmount().equals(0f)) {
      securityOrderDao.deleteById(traderId);
      accountDao.deleteById(traderId);
      traderDao.deleteById(traderId);
    } else {
      throw new RuntimeException("A trader can be deleted iff it has no open position and 0 cash balance.");
    }
  }

  public Account deposit(Integer traderId, Float fund) {
    if(fund < 0) {
      throw new IllegalArgumentException("Fund must be a positive value.");
    }
    account = accountDao.findByTraderId(traderId);
    account.setAmount(account.getAmount() + fund);
    accountDao.updateOne(account);
    return account;
  }

  public Account withdraw(Integer traderId, Float fund) {
    if(fund < 0) {
      throw new IllegalArgumentException("Fund must be a positive value.");
    }

    account = accountDao.findByTraderId(traderId);
    if (fund > account.getAmount()) {
      throw new IllegalArgumentException("Cannot withdraw a value higher than the existing account amount.");
    }

    account.setAmount(account.getAmount() - fund);
    accountDao.updateOne(account);
    return account;
  }
}
