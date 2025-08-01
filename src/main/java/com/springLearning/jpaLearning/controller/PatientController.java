package com.springLearning.jpaLearning.controller;
import com.springLearning.jpaLearning.dto.AppointmentResponseDto;
import com.springLearning.jpaLearning.dto.CreateAppointmentRequestDto;
import com.springLearning.jpaLearning.dto.PatientResponseDto;
import com.springLearning.jpaLearning.service.AppointmentService;
import com.springLearning.jpaLearning.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 1L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
    @GetMapping("/all")
    private ResponseEntity<List<PatientResponseDto>> getAllPatient(){
        return ResponseEntity.ok(patientService.getAllPatients(0,5));
    }

}