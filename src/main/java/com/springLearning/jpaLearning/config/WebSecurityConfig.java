package com.springLearning.jpaLearning.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;


//    By default security filter makes every request authenticated
@Bean
public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/public/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    // 2. ADDED RULE: Allow users with DOCTOR or ADMIN role to access /doctors/**
                    .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
                    // Add a rule for any other request to be authenticated
                    .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());
    return httpSecurity.build();
}

    @Bean
    UserDetailsService userDetailsService(){
        // Spring will automatically use the passwordEncoder bean defined above
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder.encode("pass"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("patient")
                .password(passwordEncoder.encode("pass"))
                .roles("PATIENT")
                .build();

        // Note: You don't have a user with the DOCTOR role yet.
        // You could add one here if needed.

        return new InMemoryUserDetailsManager(user1, user2);
    }

}
