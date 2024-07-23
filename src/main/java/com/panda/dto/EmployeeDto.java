package com.panda.dto;

import com.panda.enums.JobTitle;
import com.panda.enums.Role;
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
public class EmployeeDto {

    private UUID id;

    private String name;

    private String surname;

    private LocalDate employmentDate;

    private JobTitle jobTitle;

    private DepartmentDto department;

    private AuthenticationDto authentication;

    private UUID humanId;

    private Role role;
}
