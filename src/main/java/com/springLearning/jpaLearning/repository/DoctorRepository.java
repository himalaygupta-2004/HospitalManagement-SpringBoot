package com.springLearning.jpaLearning.repository;

import com.springLearning.jpaLearning.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}