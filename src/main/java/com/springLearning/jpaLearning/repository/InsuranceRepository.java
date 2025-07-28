package com.springLearning.jpaLearning.repository;

import com.springLearning.jpaLearning.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
  }