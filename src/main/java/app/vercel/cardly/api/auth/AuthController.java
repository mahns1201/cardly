package app.vercel.cardly.api.auth;

import static app.vercel.cardly.common.ApiVersion.API_V1;

import app.vercel.cardly.api.auth.dto.AuthResponseDto;
import app.vercel.cardly.api.auth.dto.RegisterRequestDto;
import app.vercel.cardly.common.TokenType;
import app.vercel.cardly.config.auth.JwtProvider;
import app.vercel.cardly.domain.user.User;
import app.vercel.cardly.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_V1 + "/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final JwtProvider jwtProvider;

  @PostMapping("/register")
  public ResponseEntity<AuthResponseDto> register(
      @RequestBody RegisterRequestDto registerRequestDto) {
    User user = authService.register(registerRequestDto);
    String token = jwtProvider.generateToken(user.getId());

    return ResponseEntity.ok(new AuthResponseDto(token, TokenType.BEARER));
  }
}
