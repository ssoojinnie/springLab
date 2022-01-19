package kr.co.songjava.mvc.controller;

import kr.co.songjava.configuration.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kakao/")
@RequiredArgsConstructor
public class KakaoController {

    //@RequiredArgsConstructor + final 선언하면 autowired 필요 없이 의존성 주입
    private final KakaoProperties kakaoProperties;

    @GetMapping("/test")
    @ResponseBody
    public KakaoProperties test() {
        return kakaoProperties;
    }

    @GetMapping("/search")
    public String search(@RequestParam String query) {
        Mono<String> mono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/address.json") ///v2/local/search/address.{FORMAT} -> FORMAT : json 또는 xml
                        .queryParam("query", query)
                        .build()
                )
                .header("Authorization", "KakaoAK " + kakaoProperties.getRestapi())
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });
        return mono.block();
    }
}
