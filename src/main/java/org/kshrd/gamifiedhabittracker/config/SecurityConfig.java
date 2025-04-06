package org.kshrd.gamifiedhabittracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static org.kshrd.gamifiedhabittracker.constant.Constant.STRENGTH_ENCODER;

@Component
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, STRENGTH_ENCODER);
    }

}
