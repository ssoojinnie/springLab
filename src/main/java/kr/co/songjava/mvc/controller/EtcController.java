package kr.co.songjava.mvc.controller;

import kr.co.songjava.framework.data.web.bind.annotation.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/terms")
    @RequestConfig(loginCheck=false, menu=true)
    public String terms(Model model){
        logger.info("terms");
        return "/terms";
    }

}
