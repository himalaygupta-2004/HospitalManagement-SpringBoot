package com.springLearning.jpaLearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;

}
