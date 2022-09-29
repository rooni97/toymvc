package toy.toymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.toymvc.domain.Member;
import toy.toymvc.domain.Music;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findByMember(Member member);
}
