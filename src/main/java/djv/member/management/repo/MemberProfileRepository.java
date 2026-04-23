package djv.member.management.repo;

import djv.member.management.entity.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfileEntity, Long> {

    MemberProfileEntity findByEmail(String email);
}
