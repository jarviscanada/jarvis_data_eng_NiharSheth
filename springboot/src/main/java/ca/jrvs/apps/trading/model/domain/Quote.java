package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Quote implements Entity<String> {

  private String ticker;
  private Double lastPrice;
  private Double bidPrice;
  private Integer bidSize;
  private Double askPrice;
  private Integer askSize;

  /**
   * Default constructor.
   */
  public Quote() {

  }

  public Quote(Quote quote) {
    this.ticker = quote.getId();
    this.lastPrice = quote.getLastPrice();
    this.bidPrice = quote.getBidPrice();
    this.bidSize = quote.getBidSize();
    this.askPrice = quote.getAskPrice();
    this.askSize = quote.getAskSize();
  }

  @Override
  public String getId() {
    return ticker;
  }

  @Override
  public void setId(String ticker) {
    this.ticker = ticker;
  }

  public String getTicker() { return ticker; }

  public void setTicker(String ticker) { this.ticker = ticker; }

  public Double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Integer getBidSize() {
    return bidSize;
  }

  public void setBidSize(Integer bidSize) {
    this.bidSize = bidSize;
  }

  public Double getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(Double askPrice) {
    this.askPrice = askPrice;
  }

  public Integer getAskSize() {
    return askSize;
  }

  public void setAskSize(Integer askSize) {
    this.askSize = askSize;
  }

  @Override
  public String toString() {
    return "quote{" +
        "\nticker=" + ticker +
        ",\nlast_price=" + lastPrice +
        ",\nbid_price=" + bidPrice +
        ",\nbid_size=" + bidSize +
        ",\nask_price=" + askPrice +
        ",\nask_size=" + askSize +
        "\n}";
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Quote)) {
      return false;
    }

    Quote quote = (Quote) o;
    return Objects.equals(getId(), quote.getId()) &&
        Objects.equals(getLastPrice(), quote.getLastPrice()) &&
        Objects.equals(getBidPrice(), quote.getBidPrice()) &&
        Objects.equals(getBidSize(), quote.getBidSize()) &&
        Objects.equals(getAskPrice(), quote.getAskPrice()) &&
        Objects.equals(getAskSize(), quote.getAskSize());
  }
}
