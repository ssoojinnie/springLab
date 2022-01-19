package kr.co.songjava.mvc.controller;

import kr.co.songjava.configuration.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao/*")
@RequiredArgsConstructor
public class KakaoController {

    //@RequiredArgsConstructor + final 선언하면 autowired 필요 없이 의존성 주입
    private final KakaoProperties properties;

    @GetMapping("/test")
    @ResponseBody
    public KakaoProperties test(){
        return properties;
    }
}
