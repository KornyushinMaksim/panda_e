package com.panda.dto;

import com.panda.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HumanDto {

    private UUID id;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate birthDay;

    private String numberPhone;

    private String email;

    private AddressDto address;

    private EmployeeDto employee;

    private Gender gender;
}

