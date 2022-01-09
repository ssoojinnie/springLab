package kr.co.songjava.mvc.service;

import kr.co.songjava.mvc.repository.Member;
import kr.co.songjava.mvc.repository.MemberMappingName;
import kr.co.songjava.mvc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //초기화되지 않은 final 필드, @nonNull 필드에 대해 생성자 생성, Autowired 없이 의존성 주입
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public Member findById(String memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원이 없습니다"));
    }

    public List<Member> findByName(String name){
        return memberRepository.findByName(name);
    }

    public List<MemberMappingName> findNameMappingByName(String name){
        return memberRepository.findNameMappingByName(name);
    }

    public boolean existsByName(String name){
        return memberRepository.existsByName(name);
    }

    public int countByName(String name){
        return memberRepository.countByName(name);
    }
}
