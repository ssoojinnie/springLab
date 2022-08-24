package kr.co.songjava.mvc.controller;

import kr.co.songjava.configuration.session.HttpSessionMember;
import kr.co.songjava.configuration.session.HttpSessionNiceauth;
import kr.co.songjava.session.domain.SessionMember;
import kr.co.songjava.session.domain.SessionNiceauth;
import kr.co.songjava.session.parameter.MemberSaveParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {

    //@RequiredArgsConstructor + final 선언하면 autowired 필요 없이 의존성 주입
    private final HttpSessionNiceauth httpSessionNiceauth;
    private final HttpSessionMember httpSessionMember;

    @PostMapping("/niceauth/response")
    @ResponseBody
    public boolean niceauthResponse(HttpServletRequest request){
        //실명인증 성공해서 응답이 온 경우
        //DB 조회해서 실제 회원인 경우
        SessionNiceauth niceauth = new SessionNiceauth();
        //인증받은 식별코드
        niceauth.setAuthId("3242213123213");
        //실제로는 SessionNiceauth 나이스 정보에서 파싱해서 사용하는데 실습에서는 하드코딩
        niceauth.setPhoneNumber("01026562657");
        niceauth.setName("홍길동");
        // 로그인 시 세션에 정보 저장
        httpSessionNiceauth.setAttribute(niceauth);
        return true;
    }

    @PostMapping("/member/signup/save")
    @ResponseBody
    public boolean signupSave(@RequestParam String memberId){
        //회원가입 예시
        SessionNiceauth niceauth = httpSessionNiceauth.getAttribute();
        //본인인증 안된경우 예외처리
        if(niceauth == null){
            throw new RuntimeException("회원가입시 본인인증은 필수 입니다.");
        }

        //DB에 저장될 정보 set + 본인인증 정보
        MemberSaveParameter member = new MemberSaveParameter();
        member.setAuthId(niceauth.getAuthId());
        member.setName(niceauth.getName());
        member.setPhoneNumber(niceauth.getPhoneNumber());
        member.setMemberId(memberId);
        //회원 DB 저장 로직 처리 후
        //본인인증 세션 초기화
        httpSessionNiceauth.removeAttribute();
        return true;
   }

   @PostMapping("/login")
   @ResponseBody
   public boolean login(@RequestParam String memberId){
        //DB 조회해서 실제 회원인 경우
       SessionMember member = new SessionMember();
       member.setMemberId(memberId);
       //로그인 시 세션에 정보 저장
       httpSessionMember.setAttribute(member);
       return true;
   }

   @GetMapping("/mypage/info")
    public String info(Model model){
        model.addAttribute("member", httpSessionMember.getAttribute());
        return "/mypage/info";
   }

   @GetMapping("/logout")
    public String logout(){
        //로그아웃 시 모든 세션 정보 삭제
       httpSessionMember.invalidate();
       return "redirect:/main";
   }
}
