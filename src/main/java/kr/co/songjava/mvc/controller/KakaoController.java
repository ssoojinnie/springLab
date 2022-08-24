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
    //private final KakaoProperties kakaoProperties;

    private final WebClient kakaoWebClient;

    @GetMapping("/search")
    public String search(@RequestParam String query) {
        /*Mono<String> mono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/address.json") ///v2/local/search/address.{FORMAT} -> FORMAT : json 또는 xml
                        .queryParam("query", query)
                        .build()
                )
                .header("Authorization", "KakaoAK " + kakaoProperties.getRestapi())
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });*/
        Mono<String> mono = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/local/search/address.json") ///v2/local/search/address.{FORMAT} -> FORMAT : json 또는 xml
                        .queryParam("query", query)
                        .build()
                )
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });
        return mono.block();
    }

    @GetMapping("/coord2regioncode")
    public String coord2regioncode(@RequestParam String x, @RequestParam String y){
        Mono<String> mono = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/local/geo/coord2regioncode.json") ///v2/local/search/address.{FORMAT} -> FORMAT : json 또는 xml
                        .queryParam("x", x)
                        .queryParam("y", y)
                        .build()
                )
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });
        return mono.block();
        /*Mono<String> mono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/address.json") ///v2/local/search/address.{FORMAT} -> FORMAT : json 또는 xml
                        .queryParam("x", x)
                        .queryParam("y", y)
                        .build()
                )
                .header("Authorization", "KakaoAK " + kakaoProperties.getRestapi())
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });*/

    }
}
