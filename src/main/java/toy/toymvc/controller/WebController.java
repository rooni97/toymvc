package toy.toymvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toy.toymvc.controller.dto.MemberInfo;
import toy.toymvc.controller.dto.MusicInfo;
import toy.toymvc.domain.Member;
import toy.toymvc.domain.Music;
import toy.toymvc.login.Login;
import toy.toymvc.service.MemberService;
import toy.toymvc.service.MusicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebController {

    private final MusicService musicService;
    private final MemberService memberService;

    public static final String LOGIN_MEMBER = "loginMember";

    @GetMapping("/")
    public String welcomePage(HttpServletRequest request, @Login Long memberId, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            Member member = memberService.findById(memberId);
            model.addAttribute("member", member);
        }
        return "index.html";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberInfo", new MemberInfo());
        return "loginForm.html";
    }

    @GetMapping("/musics/add")
    public String addMusicForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
        }
        model.addAttribute("form", new MusicInfo());
        return "/musics/addMusicForm.html";
    }

    @PostMapping("/musics/add")
    public String addMusic(Model model, @Login Long memberId, @ModelAttribute MusicInfo musicInfo) {
        if (memberId == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
        }

        Music music = new Music();
        music.setArtistName(musicInfo.getArtistName());
        music.setPlayName(musicInfo.getPlayName());
        music.setUrl(musicInfo.getUrl());
        Member member = memberService.findById(memberId);
        music.setMember(member);
        member.getMusicList().add(music);
        musicService.save(music);
        log.info("MusicInfo={}", musicInfo);

        return "redirect:/musics";
    }

    @GetMapping("/musics")
    public String musicList(HttpServletRequest request, @Login Long memberId, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
        }
        List<Music> musics = musicService.findAllByMember(memberId);
        model.addAttribute("musics", musics);

        return "/musics/musicList.html";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @ModelAttribute MemberInfo memberInfo,
                        BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURI) {

        log.info("memberInfo={}", memberInfo);
        Member member = memberService.findByUsernameAndPassword(
                memberInfo.getUsername(), memberInfo.getPassword());

        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, member.getId());

        return "redirect:" + redirectURI;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
