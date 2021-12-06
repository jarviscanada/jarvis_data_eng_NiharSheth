package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Account implements Entity<Integer> {

  private Integer id;
  private Integer traderId;
  private Float amount;

  /**
   * Default constructor.
   */
  public Account() {

  }

  public Account(Account account) {
    this.id = account.getId();
    this.traderId = account.getTraderId();
    this.amount = account.getAmount();
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTraderId() {
    return traderId;
  }

  public void setTraderId(Integer traderId) {
    this.traderId = traderId;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "account{" +
        "\nid=" + id +
        "\ntrader_id=" + traderId +
        "\namount=" + amount +
        "\n}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Account)) {
      return false;
    }

    Account account = (Account) o;
    return Objects.equals(getId(), account.getId()) &&
        Objects.equals(getTraderId(), account.getTraderId()) &&
        Objects.equals(getAmount(), account.getAmount());
  }
}
