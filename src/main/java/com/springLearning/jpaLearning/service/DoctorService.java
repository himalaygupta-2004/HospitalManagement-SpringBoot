package com.springLearning.jpaLearning.service;

import com.springLearning.jpaLearning.dto.DoctorResponseDto;
import com.springLearning.jpaLearning.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }
}
