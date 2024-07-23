package com.panda.dto;

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
public class FileToDataBaseDto {

    private UUID id;

    private String nameFile;

    private long size;

    private String typeFile;

    private LocalDate dateOfChange;

    private String pathToStorage;

    private boolean isActive;

    private EmployeeDto employee;
}
