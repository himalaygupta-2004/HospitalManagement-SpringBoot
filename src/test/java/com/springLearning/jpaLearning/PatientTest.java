package com.springLearning.jpaLearning;

import com.springLearning.jpaLearning.dto.BloodGroupCountResponseEntity;
import com.springLearning.jpaLearning.entity.Patient;
import com.springLearning.jpaLearning.entity.type.BloodGroupType;
import com.springLearning.jpaLearning.repository.PatientRepository;
import com.springLearning.jpaLearning.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest

public class PatientTest {

    @Autowired
    private  PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){

        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);

    }

    @Test
    public void testTransactionMethods(){
//        Patient patient = patientService.getPatient(1L);

//        Patient patient = patientRepository.findById(1L).orElseThrow();
//        System.out.println(patient);

//        Patient patient = patientRepository.findByName("Myra Gupta");
//        System.out.println(patient);
//        List<Patient> p2 = patientRepository.findByNameOrEmail("Myra Gupta","myra.gupta@example.com");
//        System.out.println(p2);

        List<Patient> p =  patientRepository.findByEmail("myra.gupta@example.com");
//        System.out.println(p);
//
//        List<Object[]> rs = patientRepository.countEachBloodGroup();
//        for(Object[] obj : rs){
//            System.out.println(obj[0]+" "+obj[1]);
//        }

//        int row = patientRepository.updateName("Himalay Gupta",1L);
//        System.out.println(row);
        Page<Patient> pat = patientRepository.findAllPatients(PageRequest.of(0,2, Sort.by("name")));
        for(Patient patt: pat){
            System.out.println(patt);
        }
//        List<Patient> pat = patientRepository.findByBloodGroup(BloodGroupType.valueOf("A_POSITIVE"));
//        System.out.println(pat);
//        List<BloodGroupCountResponseEntity> rs = patientRepository.countEachBloodGroupType();
//        for(BloodGroupCountResponseEntity obj : rs){
//            System.out.println(obj);
//        }
    }
}
