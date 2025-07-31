package com.springLearning.jpaLearning;

import com.springLearning.jpaLearning.entity.Appointment;
import com.springLearning.jpaLearning.entity.Insurance;
import com.springLearning.jpaLearning.entity.Patient;
import com.springLearning.jpaLearning.service.AppointmentService;
import com.springLearning.jpaLearning.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuraceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

//        Disassociation
        var newPatient = insuranceService.disaccociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

//    @Test
//    public void TestCreateAppointment( ){
//        Appointment appointment = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2025,11,1,14,00,00))
//                .reason("Cancer")
//                .build();
//
//        var newAppointment = appointmentService.createNewAppointment(appointment,1L,2L);
//        System.out.println(newAppointment);
    }


}
