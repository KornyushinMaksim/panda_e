package com.panda.model;

import com.panda.enums.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "city")
//    @Enumerated(EnumType.ORDINAL)
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home")
    private Integer home;

    @Column(name = "apartment")
    private Integer apartment;
}
