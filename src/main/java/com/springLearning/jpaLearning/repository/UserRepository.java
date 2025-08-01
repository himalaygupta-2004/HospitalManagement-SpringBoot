package com.springLearning.jpaLearning.repository;

import com.springLearning.jpaLearning.entity.User;
import com.springLearning.jpaLearning.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}