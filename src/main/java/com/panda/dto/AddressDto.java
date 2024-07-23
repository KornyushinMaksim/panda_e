package com.panda.dto;

import com.panda.enums.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private UUID id;

    private String city;

    private String street;

    private Integer home;

    private Integer apartment;
}
