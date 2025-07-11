package app.vercel.cardly.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleType {
  ADMIN("ADMIN"),
  USER("USER");

  private final String value;

  RoleType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
