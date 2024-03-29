package kr.co.songjava.mvc.controller;

import kr.co.songjava.mvc.repository.Member;
import kr.co.songjava.mvc.repository.MemberType;
import kr.co.songjava.mvc.repository.MemberMappingName;
import kr.co.songjava.mvc.service.JPAMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/jpa/*")
@RequiredArgsConstructor
public class JPAMemberController {

    private final JPAMemberService JPAMemberService;

    @GetMapping("/save")
    public Member save(){
        Member member = new Member();
        member.setMemberId("user12345");
        member.setMemberType(MemberType.S);
        member.setEmail("test@naver.com");
        member.setZipcode("0123122222");
        member.setAddress("서울");
        member.setAddressDetail("서초구");
        member.setJoinDate(Calendar.getInstance().getTime());
        member.setName("홍길동");
        member.setPassword("test");
        member.setPhoneNumber("01026562657");
        member.setUpdateDate(Calendar.getInstance().getTime());

        JPAMemberService.save(member);
        return member;
    }

    @GetMapping("/findById/{memberId}")
    public Member findById(@PathVariable String memberId){
        return JPAMemberService.findById(memberId);
    }

    @GetMapping("/findByName/{name}")
    public List<Member> findByName(@PathVariable String name){
        return JPAMemberService.findByName(name);
    }

    @GetMapping("/findNameMappingByName/{name}")
    public List<MemberMappingName> findNameMappingByName(@PathVariable String name){
        return JPAMemberService.findNameMappingByName(name);
    }

    @GetMapping("/existsByName/{name}")
    public boolean existsByName(@PathVariable String name){
        return JPAMemberService.existsByName(name);
    }

    @GetMapping("/countByName/{name}")
    public int countByName(@PathVariable String name){
        return JPAMemberService.countByName(name);
    }

}
