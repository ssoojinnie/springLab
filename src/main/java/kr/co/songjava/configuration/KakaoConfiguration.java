package kr.co.songjava.configuration;

import kr.co.songjava.configuration.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class KakaoConfiguration {

    private final KakaoProperties kakaoProperties;

    @Bean
    public WebClient kakaoWebClient(){
       return WebClient.builder().baseUrl("https://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK " + kakaoProperties.getRestapi()).build();

    }
}
