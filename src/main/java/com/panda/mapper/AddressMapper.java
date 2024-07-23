package com.panda.mapper;

import com.panda.dto.AddressDto;
import com.panda.dto.AddressDto;
import com.panda.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
