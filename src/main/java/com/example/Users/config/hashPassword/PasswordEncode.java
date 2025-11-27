package com.example.Users.config.hashPassword;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncode {

    @Bean
    public PasswordEncoder argon2PasswordEncoder() {
        return new Argon2PasswordEncoder(
                16,
                32,
                4,
                1 << 16,
                3
        );
    }
}
