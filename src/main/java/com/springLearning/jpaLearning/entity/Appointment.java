package com.springLearning.jpaLearning.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

//    Patient Connection -- Uni Directional
    @ManyToOne
//    Many appointments to one patient
    @ToString.Exclude
    @JoinColumn(name = "patient_id",nullable = false) //Patient is required and not nullable
    private Patient patient;

//    Owning side
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;


}