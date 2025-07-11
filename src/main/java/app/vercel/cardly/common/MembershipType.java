package app.vercel.cardly.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MembershipType {
  INTERN("INTERN"),
  STAFF("STAFF"),
  MANAGER("MANAGER");

  private final String value;

  MembershipType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
