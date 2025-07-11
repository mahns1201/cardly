package app.vercel.cardly.domain.user;

import app.vercel.cardly.domain.role.Role;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_role")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {
  @Builder.Default @EmbeddedId private UserRoleId id = new UserRoleId();

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("roleId")
  private Role role;

  @Embeddable
  @Getter
  @Setter
  @EqualsAndHashCode
  public static class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
  }
}
