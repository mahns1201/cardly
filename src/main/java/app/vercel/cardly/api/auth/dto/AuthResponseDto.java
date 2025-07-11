package app.vercel.cardly.api.auth.dto;

import app.vercel.cardly.common.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDto {
  private String accessToken;
  private TokenType tokenType;
}
