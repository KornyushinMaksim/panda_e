package com.panda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_to_database")
public class FileToDataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_file")
    private String nameFile;

    @Column(name = "size_file")
    private long size;

    @Column(name = "type")
    private String typeFile;

    @Column(name = "date_of_change")
    private LocalDate dateOfChange;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "path_to_storage")
    private String pathToStorage;

    @Column(name = "is_active")
    private Boolean isActive;
}
