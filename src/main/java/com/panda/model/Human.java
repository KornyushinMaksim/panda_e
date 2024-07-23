package com.panda.model;

import com.panda.enums.Gender;
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
@Table(name = "humans")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    @JoinColumn(name = "employee_id")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Employee employee;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
