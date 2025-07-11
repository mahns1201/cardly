package app.vercel.cardly.api.auth.dto;

import lombok.Getter;

@Getter
public class RegisterRequestDto {
  private String email;
  private String password;
  private String name;
}
