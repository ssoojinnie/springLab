package kr.co.songjava.configuration;

import kr.co.songjava.configuration.properties.KakaoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties({
        KakaoProperties.class
})
@PropertySource({
        "classpath:properties/kakao.properties"
})
public class PropertiesConfiguration {
    //propertysource 값을 KakaoProperties.class 에 값을 주입
}
