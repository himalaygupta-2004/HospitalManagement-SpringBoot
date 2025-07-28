package com.springLearning.jpaLearning.service;

import com.springLearning.jpaLearning.entity.Appointment;
import com.springLearning.jpaLearning.entity.Doctor;
import com.springLearning.jpaLearning.entity.Patient;
import com.springLearning.jpaLearning.repository.AppointmentRepository;
import com.springLearning.jpaLearning.repository.DoctorRepository;
import com.springLearning.jpaLearning.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    //RestApi agar banate to yaha pe dto ka input lete rather than entity for json format response.
    @Transactional
    public Appointment createNewAppointment(Appointment appointment,Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId()!=null) throw new IllegalArgumentException("Appointment should not have Id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        //To maintain consistency
        patient.getAppointment().add(appointment);

       return appointmentRepository.save(appointment);
    }

    @Transactional
    public void reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
    }
}
