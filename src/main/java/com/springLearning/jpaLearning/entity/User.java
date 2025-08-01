package com.springLearning.jpaLearning.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @JoinColumn(unique = true)
    private String username;
    private String password;


//    Used for Authorization part
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}




