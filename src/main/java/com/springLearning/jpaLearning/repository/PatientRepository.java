package com.springLearning.jpaLearning.repository;

import com.springLearning.jpaLearning.dto.BloodGroupCountResponseEntity;
import com.springLearning.jpaLearning.entity.Patient;
import com.springLearning.jpaLearning.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long >{

    Patient findByName(String name);
//    Parameter nomenclature should be same as entity layer
    List<Patient> findByNameOrEmail(String name, String email);

    @Query("SELECT p FROM Patient p where p.email = :email")
    List<Patient> findByEmail(@Param("email") String email);

    @Query("SELECT new com.springLearning.jpaLearning.dto.BloodGroupCountResponseEntity(p.bloodGroup , COUNT(p)) FROM Patient p group by p.bloodGroup")
//    List<Object[]> countEachBloodGroup();
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "SELECT * FROM PATIENT",nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name =:name where p.id=:id")
    int updateName(@Param("name") String name, @Param("id")Long id);


//    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointment a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointment ")
    List<Patient> findAllPatientWithAppointment();
}
