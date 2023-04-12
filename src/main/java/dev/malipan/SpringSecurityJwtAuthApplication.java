package dev.malipan;

import dev.malipan.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties( RsaKeyProperties.class )
@SpringBootApplication
public class SpringSecurityJwtAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtAuthApplication.class, args);
    }

}
