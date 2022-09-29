package toy.toymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.toymvc.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsernameAndPassword(String username, String password);
}
