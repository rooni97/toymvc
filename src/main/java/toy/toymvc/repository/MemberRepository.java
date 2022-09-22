package toy.toymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.toymvc.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
