package com.springLearning.jpaLearning.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2Successhandler oAuth2Successhandler;

//    By default security filter makes every request authenticated
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .sessionManagement(sessionConfig-> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**","/auth/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        // 2. ADDED RULE: Allow users with DOCTOR or ADMIN role to access /doctors/**
//                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
                        // Add a rule for any other request to be authenticated
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oAuth2 -> oAuth2
                        .failureHandler(
                        (request, response, exception) -> {
                            log.error("OAuth2 Error : {}",exception.getMessage());
                        })
                        .successHandler(oAuth2Successhandler)
                );
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }



}
