package app.vercel.cardly.domain.membership;

import app.vercel.cardly.common.MembershipType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
  Optional<Membership> findByName(MembershipType name);
}
