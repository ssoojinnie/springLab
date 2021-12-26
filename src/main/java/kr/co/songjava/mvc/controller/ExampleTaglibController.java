package kr.co.songjava.mvc.controller;

import kr.co.songjava.mvc.domain.BoardType;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/example/taglib")
public class ExampleTaglibController {
    @RequestMapping("/search")
    public void search(BoardSearchParameter parameter, Model model){
        model.addAttribute("boardTypes", BoardType.values()); //BoardType이라는 Enum의 값들을 배열 형태로 리턴
        model.addAttribute("parameter", parameter);
    }
}
