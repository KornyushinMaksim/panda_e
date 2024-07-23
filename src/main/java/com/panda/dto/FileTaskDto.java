package com.panda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileTaskDto {

    private UUID id;

    private String nameTask;

    private FileToDataBaseDto fileId;

    private String authorTask;

    private String customerName;

    private Set<EmployeeDto> executors;

    private LocalDate deadLine;

    private Long numbering;
}
