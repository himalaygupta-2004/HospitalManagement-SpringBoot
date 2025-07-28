package com.springLearning.jpaLearning.repository;

import com.springLearning.jpaLearning.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}