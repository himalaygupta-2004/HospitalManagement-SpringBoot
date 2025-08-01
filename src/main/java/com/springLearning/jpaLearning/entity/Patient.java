package com.springLearning.jpaLearning.entity;
import com.springLearning.jpaLearning.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table( //name = "patient",
        uniqueConstraints = {
        @UniqueConstraint(name = "unique_patient_email",columnNames = {"email"})
}
//        indexes = {
//        @Index(name = "idx_patient_id",columnList ="id" )
//        }
)

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 40)
    private String name;

//    private LocalDate dob;

    @Column(nullable = false)
    private String email;

    private String gender;

    @OneToOne
    @MapsId
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

//    Owning side
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id",unique = true)
    private Insurance insurance;

//    Inverse Side
    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},orphanRemoval = true )
    @ToString.Exclude
    private List<Appointment> appointment = new ArrayList<>();

//    If we just want to return patient we donot need to get the list of appoitnments , we can change the fetch type from Eager to lazy




}
