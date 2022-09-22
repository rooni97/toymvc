package toy.toymvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.toymvc.domain.Member;
import toy.toymvc.domain.Music;
import toy.toymvc.repository.MusicRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MusicService {

    private final MusicRepository musicRepository;

    public Long save(Music music) {
        musicRepository.save(music);
        return music.getId();
    }

    public Music findById(Long id) {
        return musicRepository.findById(id)
                .orElse(null);
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }
}
