package com.C10G14.WorldFitBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String email;
    private String clientSince;
    private List<String> roles;
    private String profileImg;
    private String weight;
    private String height;
    private String sex;
    private int age;
    private List<RoutineResponseDto> routines;
}
