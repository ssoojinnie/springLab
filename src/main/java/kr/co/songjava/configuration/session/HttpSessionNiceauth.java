package kr.co.songjava.configuration.session;

import kr.co.songjava.session.domain.SessionNiceauth;
import org.springframework.stereotype.Component;

@Component
public class HttpSessionNiceauth extends AbstractHttpSession<SessionNiceauth> {

    @Override
    protected String name(){
        return "SESSION_NICEAUTH";
    }
}
