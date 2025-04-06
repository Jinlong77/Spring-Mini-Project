package org.kshrd.gamifiedhabittracker.security;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.filter.JwtAuthFilter;
import org.kshrd.gamifiedhabittracker.handler.CustomAccessDeniedHandler;
import org.kshrd.gamifiedhabittracker.jwt.JwtAuthEntryPoint;
import org.kshrd.gamifiedhabittracker.security.provider.ApiAuthenticationProvider;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.kshrd.gamifiedhabittracker.constant.Constant.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class AuthFilterChain {

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtAuthFilter jwtAuthFilter;
    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        ApiAuthenticationProvider provider = new ApiAuthenticationProvider(appUserService, passwordEncoder);
        return new ProviderManager(provider);
    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( req -> req
                        .requestMatchers(
                                AUTH_API + "/register",
                                AUTH_API + "/login",
                                AUTH_API + "/verify",
                                AUTH_API + "/resend",
                                "/api/v1/files/preview-file",
                                "/api/v1/files/upload-file",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}