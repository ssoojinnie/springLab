package kr.co.songjava.examplespring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "kr.co.songjava")
@EnableScheduling
@Controller
public class ExampleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringApplication.class, args);
	}

	@GetMapping("/")
	public String home(Model model, @RequestParam String menu){
		model.addAttribute("menu", menu);
		model.addAttribute("name", "자동완성전 text");
		model.addAttribute("values", Arrays.asList("Spring", "Spring Boot", "Vue.JS", "React"));
		return "thymeleaf/example1";
	}
}
