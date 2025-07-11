package app.vercel.cardly.domain.membership;

import app.vercel.cardly.common.MembershipType;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "membership")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true, length = 30)
  private MembershipType name;

  @Column(length = 100)
  private String description;

  @Column(nullable = false)
  private int price;

  @Lob private String benefits;

  @Column(name = "limit_card_owned", nullable = false)
  private int limitCardOwned;

  @Column(name = "limit_card_received", nullable = false)
  private int limitCardReceived;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }
}
