package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "id_str",
    "indices",
    "name",
    "screen_name"
})

public class UserMention {

  @JsonProperty("id")
  private int id;
  @JsonProperty("id_str")
  private String id_str;
  @JsonProperty("indices")
  private int[] indices;
  @JsonProperty("name")
  private String name;
  @JsonProperty("screen_name")
  private String screen_name;

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(int id) {
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

  @JsonProperty("indices")
  public int[] getIndices() {
    return indices;
  }

  @JsonProperty("indices")
  public void setIndices(int[] indices) {
    this.indices = indices;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("screen_name")
  public String getScreen_name() {
    return screen_name;
  }

  @JsonProperty("screen_name")
  public void setScreen_name(String screen_name) {
    this.screen_name = screen_name;
  }
}
