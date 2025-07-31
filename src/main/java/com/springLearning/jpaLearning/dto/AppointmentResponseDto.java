package com.springLearning.jpaLearning.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    //    private PatientResponseDto patient;
    private DoctorResponseDto doctor;
}
