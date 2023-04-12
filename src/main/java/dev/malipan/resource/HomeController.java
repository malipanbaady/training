package dev.malipan.resource;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication){
        return "Hello, "+ authentication.getName()+"!";
    }
}
