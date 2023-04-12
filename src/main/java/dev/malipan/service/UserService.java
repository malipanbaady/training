package dev.malipan.service;

import dev.malipan.model.LoginRequest;
import dev.malipan.model.UserApp;
import dev.malipan.repo.UserAppRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppRepo userAppRepo;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public void register(LoginRequest request){
        UserApp userApp = UserApp.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();
         userApp = userAppRepo.save(userApp);

        tokenService.generateToken(userApp);
    }

    public String login(LoginRequest loginRequest){
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(),
                        loginRequest.password())
        );

        return tokenService.generateToken(
                UserApp.builder()
                        .password(loginRequest.password())
                        .username(loginRequest.username())
                        .build()
        );
    }
}
