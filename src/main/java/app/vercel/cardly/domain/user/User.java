package app.vercel.cardly.domain.user;

import app.vercel.cardly.domain.membership.Membership;
import app.vercel.cardly.domain.role.Role;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  public boolean isCorrectPassword(
      String rawPassword, org.springframework.security.crypto.password.PasswordEncoder encoder) {
    return encoder.matches(rawPassword, this.password);
  }

  @Column(nullable = false, length = 50)
  private String name;

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserRole> userRoles = new ArrayList<>();

  public void addRole(Role role) {
    UserRole userRole = UserRole.builder().user(this).role(role).build();
    userRoles.add(userRole);
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "membership_id")
  private Membership membership;
}
