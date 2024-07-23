package com.panda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.panda.enums.JobTitle;
import com.panda.enums.Role;
import com.panda.service.AuthenticationService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "employment_date")
    private LocalDate employmentDate;

    @Column(name = "job_title")
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    @JoinColumn(name = "department_id")
    private UUID departmentId;

    @Column(name = "human_id")
    private UUID humanId;

    @OneToOne
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

//    @ManyToMany
//    @JoinTable(name = "file_employee_relation",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "file_task_id"))
//    private Set<FileTask> tasks;


//    private Boolean access;//доступ

}
