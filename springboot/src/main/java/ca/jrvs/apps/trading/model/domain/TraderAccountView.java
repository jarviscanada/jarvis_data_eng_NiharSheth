package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class TraderAccountView {

  private Trader trader;
  private Account account;

  /**
   * Default constructor
   */
  public TraderAccountView() {

  }

  public TraderAccountView(Trader trader, Account account) {
    this.trader = trader;
    this.account = account;
  }

  public Trader getTrader() {
    return trader;
  }

  public void setTrader(Trader trader) {
    this.trader = trader;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return "trader_account_view{" +
        "\ntrader=" + trader +
        "\naccount=" + account +
        "\n}";
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof SecurityOrder)) {
      return false;
    }

    TraderAccountView traderAccountView = (TraderAccountView) o;
    return Objects.equals(getTrader(), traderAccountView.getTrader()) &&
        Objects.equals(getAccount(), traderAccountView.getAccount());
  }
}
