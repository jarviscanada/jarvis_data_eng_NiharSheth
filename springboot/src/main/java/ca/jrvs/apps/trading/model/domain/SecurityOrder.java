package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class SecurityOrder implements Entity<Integer> {

  private Integer id;
  private Integer accountId;
  private String status;
  private String ticker;
  private Integer size;
  private Float price;
  private String notes;

  /**
   * Default constructor.
   */
  public SecurityOrder() {

  }

  public SecurityOrder(SecurityOrder securityOrder) {
    this.id = securityOrder.getId();
    this.accountId = securityOrder.getAccountId();
    this.status = securityOrder.getStatus();
    this.ticker = securityOrder.getTicker();
    this.size = securityOrder.getSize();
    this.price = securityOrder.getPrice();
    this.notes = securityOrder.getNotes();
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return "securty_order{" +
        "\nid=" + id +
        "\naccount_id=" + accountId +
        "\nstatus=" + status +
        "\nticker=" + ticker +
        "\nsize=" + size +
        "\nprice=" + price +
        "\nnotes=" + notes +
        "\n}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof SecurityOrder)) {
      return false;
    }

    SecurityOrder securityOrder = (SecurityOrder) o;
    return Objects.equals(getId(), securityOrder.getId()) &&
        Objects.equals(getAccountId(), securityOrder.getAccountId()) &&
        Objects.equals(getStatus(), securityOrder.getStatus()) &&
        Objects.equals(getTicker(), securityOrder.getTicker()) &&
        Objects.equals(getSize(), securityOrder.getSize()) &&
        Objects.equals(getPrice(), securityOrder.getPrice()) &&
        Objects.equals(getNotes(), securityOrder.getNotes());
  }
}
