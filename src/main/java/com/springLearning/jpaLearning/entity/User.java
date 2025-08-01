package com.springLearning.jpaLearning.entity;

import com.springLearning.jpaLearning.entity.type.AuthProviderType;
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
@Table(indexes = {
        @Index(name = "provider_id_provider_type",columnList ="providerId , providerType" )
} )
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @JoinColumn(unique = true)
    private String username;
    private String password;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;


//    Used for Authorization part
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}




