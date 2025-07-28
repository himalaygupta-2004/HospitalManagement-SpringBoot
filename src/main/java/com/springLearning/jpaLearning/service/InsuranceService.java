package com.springLearning.jpaLearning.service;


import com.springLearning.jpaLearning.entity.Insurance;
import com.springLearning.jpaLearning.entity.Patient;
import com.springLearning.jpaLearning.repository.InsuranceRepository;
import com.springLearning.jpaLearning.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ToString
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional // ya to sab perform hoga ya to kuch nahi
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient Id not found"));

        //Dirtification of patient
        patient.setInsurance(insurance);

        insurance.setPatient(patient); //Bi directional Consistency maintainance
        //Cascading -- jab bhi parent identity me kuch changes ho to child entity kaise behave kre

        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: "+patientId));

        patient.setInsurance(null);
        return patient;
    }
    // HW
    //Create three appointment for a patient and then delete Patient
}
