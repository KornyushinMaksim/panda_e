package com.panda.mapper;

import com.panda.dto.AuthenticationDto;import com.panda.dto.AuthenticationDto;
import com.panda.model.Authentication;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthenticationMapper {

    AuthenticationDto toDto(Authentication authentication);

    Authentication toEntity(AuthenticationDto authenticationDto);

    Authentication merge(AuthenticationDto dto, @MappingTarget Authentication entity);
}
