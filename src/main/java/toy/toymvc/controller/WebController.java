package toy.toymvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toy.toymvc.controller.dto.MusicInfo;
import toy.toymvc.domain.Member;
import toy.toymvc.domain.Music;
import toy.toymvc.repository.MemberRepository;
import toy.toymvc.service.MusicService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebController {

    private final MusicService musicService;

    @GetMapping("/")
    public String welcomePage() {
        return "index.html";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm.html";
    }

    @GetMapping("/musics/add")
    public String addMusicForm(Model model) {
        model.addAttribute("form", new MusicInfo());
        return "/musics/addMusicForm.html";
    }

    @PostMapping("/musics/add")
    public String addMusic(@ModelAttribute MusicInfo musicInfo) {
        Music music = new Music();
        music.setArtistName(musicInfo.getArtistName());
        music.setPlayName(musicInfo.getPlayName());
        music.setUrl(musicInfo.getUrl());

        musicService.save(music);
        log.info("MusicInfo={}", musicInfo);

        return "redirect:/musics";
    }

    @GetMapping("/musics")
    public String musicList(Model model) {
        List<Music> musics = musicService.findAll();
        model.addAttribute("musics", musics);

        return "/musics/musicList.html";
    }
}
