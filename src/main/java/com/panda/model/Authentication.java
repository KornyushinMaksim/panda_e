package com.panda.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authentications")
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "employee_id")
    private UUID employeeId;
}
