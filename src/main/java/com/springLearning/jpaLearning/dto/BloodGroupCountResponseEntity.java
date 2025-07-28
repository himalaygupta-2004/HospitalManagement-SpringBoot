package com.springLearning.jpaLearning.dto;


import com.springLearning.jpaLearning.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;
    private Long count;

}

