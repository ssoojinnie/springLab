package kr.co.songjava.mvc.controller;

import kr.co.songjava.mvc.domain.Member;
import kr.co.songjava.mvc.domain.MemberType;
import kr.co.songjava.mvc.repository.MemberMappingName;
import kr.co.songjava.mvc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public Member save(){
        Member member = new Member();
        member.setMemberId("user12345");
        member.setMemberType(MemberType.S);
        member.setEmail("test@naver.com");
        memberService.save(member);
        return member;
    }

    @GetMapping("/findById/{memberId}")
    public Member findById(@PathVariable String memberId){
        return memberService.findById(memberId);
    }

    @GetMapping("/findByName/{name}")
    public List<Member> findByName(@PathVariable String name){
        return memberService.findByName(name);
    }

    @GetMapping("/findNameMappingByName/{name}")
    public List<MemberMappingName> findNameMappingByName(@PathVariable String name){
        return memberService.findNameMappingByName(name);
    }

    @GetMapping("/existsByName/{name}")
    public boolean existsByName(@PathVariable String name){
        return memberService.existsByName(name);
    }

    @GetMapping("/countByName/{name}")
    public int countByName(@PathVariable String name){
        return memberService.countByName(name);
    }

}
