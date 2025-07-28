package com.springLearning.jpaLearning.repository;

import com.springLearning.jpaLearning.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}