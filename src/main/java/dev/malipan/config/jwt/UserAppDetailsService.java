package dev.malipan.config.jwt;

import dev.malipan.repo.UserAppRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAppDetailsService implements UserDetailsService {

    private final UserAppRepo userAppRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepo.findUserAppByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User %s not found".formatted(username)));
    }
}
