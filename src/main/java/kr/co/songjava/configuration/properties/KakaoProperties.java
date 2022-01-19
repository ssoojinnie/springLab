package kr.co.songjava.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class KakaoProperties {
    //properties 에 있는 값과 변수를 매핑하기 위한 클래스

    private String restapi;
    private String javascript;
    private String clientSecret;
}
