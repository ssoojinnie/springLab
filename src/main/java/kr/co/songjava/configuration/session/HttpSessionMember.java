package kr.co.songjava.configuration.session;

import kr.co.songjava.session.domain.SessionMember;
import org.springframework.stereotype.Component;

@Component
public class HttpSessionMember extends AbstractHttpSession<SessionMember>{

    //AbstractHttpSession 클래스에 공통기능 정의
    //상속받고,
    // HttpSessionNiceauth 와는 name 만 다르고 기능은 동일하니 name() 메소드만 재정의
    @Override
    protected String name(){
        return "SESSION_MEMBER";
    }

}
