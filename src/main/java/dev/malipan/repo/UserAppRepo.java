package dev.malipan.repo;

import dev.malipan.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepo extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findUserAppByUsername(String username);
}