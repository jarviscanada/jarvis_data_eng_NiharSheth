package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonIgnoreProperties({
    "source",
    "truncated",
    "in_reply_to_status_id",
    "in_reply_to_status_id_str",
    "in_reply_to_user_id",
    "in_reply_to_user_id_str",
    "in_reply_to_screen_name",
    "user",
    "place",
    "quoted_status_id",
    "quoted_status_id_str",
    "is_quote_status",
    "quoted_status",
    "retweeted_status",
    "quote_count",
    "reply_count",
    "extended_entities",
    "possibly_sensitive",
    "filter_level",
    "lang",
    "matching_rules",
    "current_user_retweet",
    "scopes",
    "withheld_copyright",
    "withheld_in_countries",
    "withheld_scope",
    "geo",
    "contributors"
})

@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "retweeted",
    "favorited"
})

public class Tweet {

  @JsonProperty("created_at")
  private String created_at;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String id_str;
  @JsonProperty("text")
  private String text;
  @JsonProperty("entities")
  private Entities entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private int retweet_count;
  @JsonProperty("favorite_count")
  private int favorite_count;
  @JsonProperty("retweeted")
  private boolean retweeted;
  @JsonProperty("favorited")
  private boolean favorited;

  @JsonProperty("created_at")
  public String getCreated_at() {
    return created_at;
  }

  @JsonProperty("created_at")
  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  @JsonProperty("id")
  public long getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(long id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getId_str() {
    return id_str;
  }

  @JsonProperty("id_str")
  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("retweet_count")
  public int getRetweet_count() {
    return retweet_count;
  }

  @JsonProperty("retweet_count")
  public void setRetweet_count(int retweet_count) {
    this.retweet_count = retweet_count;
  }

  @JsonProperty("favorite_count")
  public int getFavorite_count() {
    return favorite_count;
  }

  @JsonProperty("favorite_count")
  public void setFavorite_count(int favorite_count) {
    this.favorite_count = favorite_count;
  }

  @JsonProperty("retweeted")
  public boolean isRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(boolean retweeted) {
    this.retweeted = retweeted;
  }

  @JsonProperty("favorited")
  public boolean isFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(boolean favorited) {
    this.favorited = favorited;
  }
}
