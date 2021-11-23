package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "avgTotalVolume",
    "calculationPrice",
    "change",
    "changePercent",
    "close",
    "closeTime",
    "companyName",
    "delayedPrice",
    "delayedPriceTime",
    "extendedChange",
    "extendedChangePercent",
    "extendedPrice",
    "extendedPriceTime",
    "high",
    "iexAskPrice",
    "iexAskSize",
    "iexBidPrice",
    "iexBidSize",
    "iexLastUpdated",
    "iexMarketPercent",
    "iexRealtimePrice",
    "iexRealtimeSize",
    "iexVolume",
    "latestPrice",
    "latestSource",
    "latestTime",
    "latestUpdate",
    "latestVolume",
    "low",
    "marketCap",
    "open",
    "peRatio",
    "previousClose",
    "primaryExchange",
    "sector",
    "symbol",
    "volume",
    "week52High",
    "week52Low",
    "ytdChange"
})
@Generated("jsonschema2pojo")
public class IexQuote {

  @JsonProperty("avgTotalVolume")
  private Integer avgTotalVolume;
  @JsonProperty("calculationPrice")
  private String calculationPrice;
  @JsonProperty("change")
  private Double change;
  @JsonProperty("changePercent")
  private Double changePercent;
  @JsonProperty("close")
  private Integer close;
  @JsonProperty("closeTime")
  private Long closeTime;
  @JsonProperty("companyName")
  private String companyName;
  @JsonProperty("delayedPrice")
  private Integer delayedPrice;
  @JsonProperty("delayedPriceTime")
  private Long delayedPriceTime;
  @JsonProperty("extendedChange")
  private Double extendedChange;
  @JsonProperty("extendedChangePercent")
  private Double extendedChangePercent;
  @JsonProperty("extendedPrice")
  private Double extendedPrice;
  @JsonProperty("extendedPriceTime")
  private Long extendedPriceTime;
  @JsonProperty("high")
  private Double high;
  @JsonProperty("iexAskPrice")
  private Double iexAskPrice;
  @JsonProperty("iexAskSize")
  private Integer iexAskSize;
  @JsonProperty("iexBidPrice")
  private Integer iexBidPrice;
  @JsonProperty("iexBidSize")
  private Integer iexBidSize;
  @JsonProperty("iexLastUpdated")
  private Long iexLastUpdated;
  @JsonProperty("iexMarketPercent")
  private Double iexMarketPercent;
  @JsonProperty("iexRealtimePrice")
  private Double iexRealtimePrice;
  @JsonProperty("iexRealtimeSize")
  private Integer iexRealtimeSize;
  @JsonProperty("iexVolume")
  private Integer iexVolume;
  @JsonProperty("latestPrice")
  private Double latestPrice;
  @JsonProperty("latestSource")
  private String latestSource;
  @JsonProperty("latestTime")
  private String latestTime;
  @JsonProperty("latestUpdate")
  private Long latestUpdate;
  @JsonProperty("latestVolume")
  private Double latestVolume;
  @JsonProperty("low")
  private Double low;
  @JsonProperty("marketCap")
  private Long marketCap;
  @JsonProperty("open")
  private Double open;
  @JsonProperty("peRatio")
  private Double peRatio;
  @JsonProperty("previousClose")
  private Double previousClose;
  @JsonProperty("primaryExchange")
  private String primaryExchange;
  @JsonProperty("sector")
  private String sector;
  @JsonProperty("symbol")
  private String symbol;
  @JsonProperty("volume")
  private Double volume;
  @JsonProperty("week52High")
  private Double week52High;
  @JsonProperty("week52Low")
  private Double week52Low;
  @JsonProperty("ytdChange")
  private Double ytdChange;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("avgTotalVolume")
  public Integer getAvgTotalVolume() {
    return avgTotalVolume;
  }

  @JsonProperty("avgTotalVolume")
  public void setAvgTotalVolume(Integer avgTotalVolume) {
    this.avgTotalVolume = avgTotalVolume;
  }

  @JsonProperty("calculationPrice")
  public String getCalculationPrice() {
    return calculationPrice;
  }

  @JsonProperty("calculationPrice")
  public void setCalculationPrice(String calculationPrice) {
    this.calculationPrice = calculationPrice;
  }

  @JsonProperty("change")
  public Double getChange() {
    return change;
  }

  @JsonProperty("change")
  public void setChange(Double change) {
    this.change = change;
  }

  @JsonProperty("changePercent")
  public Double getChangePercent() {
    return changePercent;
  }

  @JsonProperty("changePercent")
  public void setChangePercent(Double changePercent) {
    this.changePercent = changePercent;
  }

  @JsonProperty("close")
  public Integer getClose() {
    return close;
  }

  @JsonProperty("close")
  public void setClose(Integer close) {
    this.close = close;
  }

  @JsonProperty("closeTime")
  public Long getCloseTime() {
    return closeTime;
  }

  @JsonProperty("closeTime")
  public void setCloseTime(Long closeTime) {
    this.closeTime = closeTime;
  }

  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }

  @JsonProperty("companyName")
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @JsonProperty("delayedPrice")
  public Integer getDelayedPrice() {
    return delayedPrice;
  }

  @JsonProperty("delayedPrice")
  public void setDelayedPrice(Integer delayedPrice) {
    this.delayedPrice = delayedPrice;
  }

  @JsonProperty("delayedPriceTime")
  public Long getDelayedPriceTime() {
    return delayedPriceTime;
  }

  @JsonProperty("delayedPriceTime")
  public void setDelayedPriceTime(Long delayedPriceTime) {
    this.delayedPriceTime = delayedPriceTime;
  }

  @JsonProperty("extendedChange")
  public Double getExtendedChange() {
    return extendedChange;
  }

  @JsonProperty("extendedChange")
  public void setExtendedChange(Double extendedChange) {
    this.extendedChange = extendedChange;
  }

  @JsonProperty("extendedChangePercent")
  public Double getExtendedChangePercent() {
    return extendedChangePercent;
  }

  @JsonProperty("extendedChangePercent")
  public void setExtendedChangePercent(Double extendedChangePercent) {
    this.extendedChangePercent = extendedChangePercent;
  }

  @JsonProperty("extendedPrice")
  public Double getExtendedPrice() {
    return extendedPrice;
  }

  @JsonProperty("extendedPrice")
  public void setExtendedPrice(Double extendedPrice) {
    this.extendedPrice = extendedPrice;
  }

  @JsonProperty("extendedPriceTime")
  public Long getExtendedPriceTime() {
    return extendedPriceTime;
  }

  @JsonProperty("extendedPriceTime")
  public void setExtendedPriceTime(Long extendedPriceTime) {
    this.extendedPriceTime = extendedPriceTime;
  }

  @JsonProperty("high")
  public Double getHigh() {
    return high;
  }

  @JsonProperty("high")
  public void setHigh(Double high) {
    this.high = high;
  }

  @JsonProperty("iexAskPrice")
  public Double getIexAskPrice() {
    return iexAskPrice;
  }

  @JsonProperty("iexAskPrice")
  public void setIexAskPrice(Double iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  @JsonProperty("iexAskSize")
  public Integer getIexAskSize() {
    return iexAskSize;
  }

  @JsonProperty("iexAskSize")
  public void setIexAskSize(Integer iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  @JsonProperty("iexBidPrice")
  public Integer getIexBidPrice() {
    return iexBidPrice;
  }

  @JsonProperty("iexBidPrice")
  public void setIexBidPrice(Integer iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  @JsonProperty("iexBidSize")
  public Integer getIexBidSize() {
    return iexBidSize;
  }

  @JsonProperty("iexBidSize")
  public void setIexBidSize(Integer iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  @JsonProperty("iexLastUpdated")
  public Long getIexLastUpdated() {
    return iexLastUpdated;
  }

  @JsonProperty("iexLastUpdated")
  public void setIexLastUpdated(Long iexLastUpdated) {
    this.iexLastUpdated = iexLastUpdated;
  }

  @JsonProperty("iexMarketPercent")
  public Double getIexMarketPercent() {
    return iexMarketPercent;
  }

  @JsonProperty("iexMarketPercent")
  public void setIexMarketPercent(Double iexMarketPercent) {
    this.iexMarketPercent = iexMarketPercent;
  }

  @JsonProperty("iexRealtimePrice")
  public Double getIexRealtimePrice() {
    return iexRealtimePrice;
  }

  @JsonProperty("iexRealtimePrice")
  public void setIexRealtimePrice(Double iexRealtimePrice) {
    this.iexRealtimePrice = iexRealtimePrice;
  }

  @JsonProperty("iexRealtimeSize")
  public Integer getIexRealtimeSize() {
    return iexRealtimeSize;
  }

  @JsonProperty("iexRealtimeSize")
  public void setIexRealtimeSize(Integer iexRealtimeSize) {
    this.iexRealtimeSize = iexRealtimeSize;
  }

  @JsonProperty("iexVolume")
  public Integer getIexVolume() {
    return iexVolume;
  }

  @JsonProperty("iexVolume")
  public void setIexVolume(Integer iexVolume) {
    this.iexVolume = iexVolume;
  }

  @JsonProperty("latestPrice")
  public Double getLatestPrice() {
    return latestPrice;
  }

  @JsonProperty("latestPrice")
  public void setLatestPrice(Double latestPrice) {
    this.latestPrice = latestPrice;
  }

  @JsonProperty("latestSource")
  public String getLatestSource() {
    return latestSource;
  }

  @JsonProperty("latestSource")
  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }

  @JsonProperty("latestTime")
  public String getLatestTime() {
    return latestTime;
  }

  @JsonProperty("latestTime")
  public void setLatestTime(String latestTime) {
    this.latestTime = latestTime;
  }

  @JsonProperty("latestUpdate")
  public Long getLatestUpdate() {
    return latestUpdate;
  }

  @JsonProperty("latestUpdate")
  public void setLatestUpdate(Long latestUpdate) {
    this.latestUpdate = latestUpdate;
  }

  @JsonProperty("latestVolume")
  public Double getLatestVolume() {
    return latestVolume;
  }

  @JsonProperty("latestVolume")
  public void setLatestVolume(Double latestVolume) {
    this.latestVolume = latestVolume;
  }

  @JsonProperty("low")
  public Double getLow() {
    return low;
  }

  @JsonProperty("low")
  public void setLow(Double low) {
    this.low = low;
  }

  @JsonProperty("marketCap")
  public Long getMarketCap() {
    return marketCap;
  }

  @JsonProperty("marketCap")
  public void setMarketCap(Long marketCap) {
    this.marketCap = marketCap;
  }

  @JsonProperty("open")
  public Double getOpen() {
    return open;
  }

  @JsonProperty("open")
  public void setOpen(Double open) {
    this.open = open;
  }

  @JsonProperty("peRatio")
  public Double getPeRatio() {
    return peRatio;
  }

  @JsonProperty("peRatio")
  public void setPeRatio(Double peRatio) {
    this.peRatio = peRatio;
  }

  @JsonProperty("previousClose")
  public Double getPreviousClose() {
    return previousClose;
  }

  @JsonProperty("previousClose")
  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
  }

  @JsonProperty("primaryExchange")
  public String getPrimaryExchange() {
    return primaryExchange;
  }

  @JsonProperty("primaryExchange")
  public void setPrimaryExchange(String primaryExchange) {
    this.primaryExchange = primaryExchange;
  }

  @JsonProperty("sector")
  public String getSector() {
    return sector;
  }

  @JsonProperty("sector")
  public void setSector(String sector) {
    this.sector = sector;
  }

  @JsonProperty("symbol")
  public String getSymbol() {
    return symbol;
  }

  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @JsonProperty("volume")
  public Double getVolume() {
    return volume;
  }

  @JsonProperty("volume")
  public void setVolume(Double volume) {
    this.volume = volume;
  }

  @JsonProperty("week52High")
  public Double getWeek52High() {
    return week52High;
  }

  @JsonProperty("week52High")
  public void setWeek52High(Double week52High) {
    this.week52High = week52High;
  }

  @JsonProperty("week52Low")
  public Double getWeek52Low() {
    return week52Low;
  }

  @JsonProperty("week52Low")
  public void setWeek52Low(Double week52Low) {
    this.week52Low = week52Low;
  }

  @JsonProperty("ytdChange")
  public Double getYtdChange() {
    return ytdChange;
  }

  @JsonProperty("ytdChange")
  public void setYtdChange(Double ytdChange) {
    this.ytdChange = ytdChange;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(IexQuote.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
    sb.append("avgTotalVolume");
    sb.append('=');
    sb.append(((this.avgTotalVolume == null)?"<null>":this.avgTotalVolume));
    sb.append(',');
    sb.append("calculationPrice");
    sb.append('=');
    sb.append(((this.calculationPrice == null)?"<null>":this.calculationPrice));
    sb.append(',');
    sb.append("change");
    sb.append('=');
    sb.append(((this.change == null)?"<null>":this.change));
    sb.append(',');
    sb.append("changePercent");
    sb.append('=');
    sb.append(((this.changePercent == null)?"<null>":this.changePercent));
    sb.append(',');
    sb.append("close");
    sb.append('=');
    sb.append(((this.close == null)?"<null>":this.close));
    sb.append(',');
    sb.append("closeTime");
    sb.append('=');
    sb.append(((this.closeTime == null)?"<null>":this.closeTime));
    sb.append(',');
    sb.append("companyName");
    sb.append('=');
    sb.append(((this.companyName == null)?"<null>":this.companyName));
    sb.append(',');
    sb.append("delayedPrice");
    sb.append('=');
    sb.append(((this.delayedPrice == null)?"<null>":this.delayedPrice));
    sb.append(',');
    sb.append("delayedPriceTime");
    sb.append('=');
    sb.append(((this.delayedPriceTime == null)?"<null>":this.delayedPriceTime));
    sb.append(',');
    sb.append("extendedChange");
    sb.append('=');
    sb.append(((this.extendedChange == null)?"<null>":this.extendedChange));
    sb.append(',');
    sb.append("extendedChangePercent");
    sb.append('=');
    sb.append(((this.extendedChangePercent == null)?"<null>":this.extendedChangePercent));
    sb.append(',');
    sb.append("extendedPrice");
    sb.append('=');
    sb.append(((this.extendedPrice == null)?"<null>":this.extendedPrice));
    sb.append(',');
    sb.append("extendedPriceTime");
    sb.append('=');
    sb.append(((this.extendedPriceTime == null)?"<null>":this.extendedPriceTime));
    sb.append(',');
    sb.append("high");
    sb.append('=');
    sb.append(((this.high == null)?"<null>":this.high));
    sb.append(',');
    sb.append("iexAskPrice");
    sb.append('=');
    sb.append(((this.iexAskPrice == null)?"<null>":this.iexAskPrice));
    sb.append(',');
    sb.append("iexAskSize");
    sb.append('=');
    sb.append(((this.iexAskSize == null)?"<null>":this.iexAskSize));
    sb.append(',');
    sb.append("iexBidPrice");
    sb.append('=');
    sb.append(((this.iexBidPrice == null)?"<null>":this.iexBidPrice));
    sb.append(',');
    sb.append("iexBidSize");
    sb.append('=');
    sb.append(((this.iexBidSize == null)?"<null>":this.iexBidSize));
    sb.append(',');
    sb.append("iexLastUpdated");
    sb.append('=');
    sb.append(((this.iexLastUpdated == null)?"<null>":this.iexLastUpdated));
    sb.append(',');
    sb.append("iexMarketPercent");
    sb.append('=');
    sb.append(((this.iexMarketPercent == null)?"<null>":this.iexMarketPercent));
    sb.append(',');
    sb.append("iexRealtimePrice");
    sb.append('=');
    sb.append(((this.iexRealtimePrice == null)?"<null>":this.iexRealtimePrice));
    sb.append(',');
    sb.append("iexRealtimeSize");
    sb.append('=');
    sb.append(((this.iexRealtimeSize == null)?"<null>":this.iexRealtimeSize));
    sb.append(',');
    sb.append("iexVolume");
    sb.append('=');
    sb.append(((this.iexVolume == null)?"<null>":this.iexVolume));
    sb.append(',');
    sb.append("latestPrice");
    sb.append('=');
    sb.append(((this.latestPrice == null)?"<null>":this.latestPrice));
    sb.append(',');
    sb.append("latestSource");
    sb.append('=');
    sb.append(((this.latestSource == null)?"<null>":this.latestSource));
    sb.append(',');
    sb.append("latestTime");
    sb.append('=');
    sb.append(((this.latestTime == null)?"<null>":this.latestTime));
    sb.append(',');
    sb.append("latestUpdate");
    sb.append('=');
    sb.append(((this.latestUpdate == null)?"<null>":this.latestUpdate));
    sb.append(',');
    sb.append("latestVolume");
    sb.append('=');
    sb.append(((this.latestVolume == null)?"<null>":this.latestVolume));
    sb.append(',');
    sb.append("low");
    sb.append('=');
    sb.append(((this.low == null)?"<null>":this.low));
    sb.append(',');
    sb.append("marketCap");
    sb.append('=');
    sb.append(((this.marketCap == null)?"<null>":this.marketCap));
    sb.append(',');
    sb.append("open");
    sb.append('=');
    sb.append(((this.open == null)?"<null>":this.open));
    sb.append(',');
    sb.append("peRatio");
    sb.append('=');
    sb.append(((this.peRatio == null)?"<null>":this.peRatio));
    sb.append(',');
    sb.append("previousClose");
    sb.append('=');
    sb.append(((this.previousClose == null)?"<null>":this.previousClose));
    sb.append(',');
    sb.append("primaryExchange");
    sb.append('=');
    sb.append(((this.primaryExchange == null)?"<null>":this.primaryExchange));
    sb.append(',');
    sb.append("sector");
    sb.append('=');
    sb.append(((this.sector == null)?"<null>":this.sector));
    sb.append(',');
    sb.append("symbol");
    sb.append('=');
    sb.append(((this.symbol == null)?"<null>":this.symbol));
    sb.append(',');
    sb.append("volume");
    sb.append('=');
    sb.append(((this.volume == null)?"<null>":this.volume));
    sb.append(',');
    sb.append("week52High");
    sb.append('=');
    sb.append(((this.week52High == null)?"<null>":this.week52High));
    sb.append(',');
    sb.append("week52Low");
    sb.append('=');
    sb.append(((this.week52Low == null)?"<null>":this.week52Low));
    sb.append(',');
    sb.append("ytdChange");
    sb.append('=');
    sb.append(((this.ytdChange == null)?"<null>":this.ytdChange));
    sb.append(',');
    sb.append("additionalProperties");
    sb.append('=');
    sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
    sb.append(',');
    if (sb.charAt((sb.length()- 1)) == ',') {
      sb.setCharAt((sb.length()- 1), ']');
    } else {
      sb.append(']');
    }
    return sb.toString();
  }

}