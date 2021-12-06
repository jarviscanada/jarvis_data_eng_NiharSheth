package ca.jrvs.apps.trading.model.domain;

import java.sql.Date;
import java.util.Objects;

public class Trader implements Entity<Integer> {

  private Integer id;
  private String firstName;
  private String lastName;
  private Date dob;
  private String country;
  private String email;

  /**
   * Default constructor.
   */
  public Trader() {

  }

  public Trader(Trader trader) {
    this.id = trader.getId();
    this.firstName = trader.getFirstName();
    this.lastName = trader.getLastName();
    this.dob = trader.getDob();
    this.country = trader.getCountry();
    this.email = trader.getEmail();
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "trader{" +
        "\nid=" + id +
        "\nfirst_name=" + firstName +
        "\nlast_name=" + lastName +
        "\ndob=" + dob +
        "\ncountry=" + country +
        "\nemail=" + email +
        "\n}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Trader)) {
      return false;
    }

    Trader trader = (Trader) o;
    return Objects.equals(getId(), trader.getId()) &&
        Objects.equals(getFirstName(), trader.getFirstName()) &&
        Objects.equals(getLastName(), trader.getLastName()) &&
        Objects.equals(getDob(), trader.getDob()) &&
        Objects.equals(getCountry(), trader.getCountry()) &&
        Objects.equals(getEmail(), trader.getEmail());
  }
}
