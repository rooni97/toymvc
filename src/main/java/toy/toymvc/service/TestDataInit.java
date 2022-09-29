package toy.toymvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.toymvc.domain.Member;
import toy.toymvc.repository.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

//    @PostConstruct
//    public void init() {
//        Member member = new Member();
//        member.setUsername("asd");
//        member.setPassword("asd");
//        memberRepository.save(member);
//    }
}
