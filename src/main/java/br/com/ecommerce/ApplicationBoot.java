package br.com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class ApplicationBoot{

   public static void main(String[] args){
      SpringApplication.run(ApplicationBoot.class, args);
   }
}
