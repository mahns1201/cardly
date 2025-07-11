package app.vercel.cardly.service;

import app.vercel.cardly.api.auth.dto.RegisterRequestDto;
import app.vercel.cardly.common.MembershipType;
import app.vercel.cardly.common.RoleType;
import app.vercel.cardly.domain.membership.Membership;
import app.vercel.cardly.domain.membership.MembershipRepository;
import app.vercel.cardly.domain.role.Role;
import app.vercel.cardly.domain.role.RoleRepository;
import app.vercel.cardly.domain.user.User;
import app.vercel.cardly.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final MembershipRepository membershipRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(RegisterRequestDto registerRequestDto) {
    if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {
      throw new IllegalArgumentException("이미 가입된 이메일입니다.");
    }

    Role role =
        roleRepository
            .findByName(RoleType.USER)
            .orElseThrow(() -> new RuntimeException("User Role이 존재하지 않습니다."));

    Membership membership =
        membershipRepository
            .findByName(MembershipType.INTERN)
            .orElseThrow(() -> new RuntimeException("INTERN Membership이 존재하지 않습니다."));

    User user =
        User.builder()
            .email(registerRequestDto.getEmail())
            .password(passwordEncoder.encode(registerRequestDto.getPassword()))
            .name(registerRequestDto.getName())
            .membership(membership)
            .build();

    user.addRole(role);

    return userRepository.save(user);
  }
}
