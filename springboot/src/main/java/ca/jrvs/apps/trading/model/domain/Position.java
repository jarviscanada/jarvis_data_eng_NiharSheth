package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Position implements Entity<Integer> {

  private Integer accountId;
  private String ticker;
  private Integer position;

  /**
   * Default constructor.
   */
  public Position() {

  }

  public Position(Position position) {
    this.accountId = position.getAccountId();
    this.ticker = position.getTicker();
    this.position = position.getPosition();
  }

  @Override
  public Integer getId() {
    return accountId;
  }

  @Override
  public void setId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "position{" +
        "\naccount_id=" + accountId +
        "\nticker=" + ticker +
        "\nposition=" + position +
        "\n}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Position)) {
      return false;
    }

    Position position = (Position) o;
    return Objects.equals(getId(), position.getId()) &&
        Objects.equals(getTicker(), position.getTicker()) &&
        Objects.equals(getPosition(), position.getPosition());
  }
}
