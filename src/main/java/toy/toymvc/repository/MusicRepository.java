package toy.toymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.toymvc.domain.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {

}
