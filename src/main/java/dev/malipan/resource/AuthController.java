package dev.malipan.resource;

import dev.malipan.model.LoginRequest;
import dev.malipan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        log.info("Login requested for user : {}", loginRequest.username());
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest loginRequest){
        log.info("Register requested for user : {}", loginRequest.username());
        userService.register(loginRequest);
        return "User %s successfully registered".formatted(loginRequest.username());
    }
}
