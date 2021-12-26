package kr.co.songjava.mvc.controller;




import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/example/parameter")
public class ExampleParameterController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/example1")
    public String example1(@RequestParam String id, @RequestParam String code, Model model){
        model.addAttribute("id", id);
        model.addAttribute("code", code);
        return "/example/parameter/example1";
    }

    /*
    Map 을 활용한 파라미터 받는 방법
     */
    @GetMapping("/example2")
    public void example2(@RequestParam Map<String, Object> paramMap, Model model){
        model.addAttribute("paramMap", paramMap);
    }


    /*
    Class 를 활용한 파라메터 받는 방법
    @param parameter
    @param model
     */
    @GetMapping("/example3")
    public void example3(ExampleParameter parameter, Model model){
        model.addAttribute("paramClass", parameter);
    }

    /*
   PathVariable 을 활용한 파라미터 받는 방법
   @param id
   @param model
    */
    @GetMapping("/example4/{id}/{code}")
    public String example4(@PathVariable String id, @PathVariable String code, Model model){
        model.addAttribute("id", id);
        model.addAttribute("code", code);
        return "/example/parameter/example4";
    }

    /*
    String[] 배열로 받는 방법
   @param id
   @param model
    */
    @GetMapping("/example5/{id}/{code}")
    public String example5(@RequestParam String[] ids, Model model){
        model.addAttribute("ids", ids);
        return "/example/parameter/example5";
    }
}
