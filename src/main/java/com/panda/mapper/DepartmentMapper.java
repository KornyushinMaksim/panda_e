package com.panda.mapper;

import com.panda.dto.DepartmentDto;import com.panda.model.Department;import com.panda.dto.DepartmentDto;
import com.panda.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);

    Department toEntity(DepartmentDto departmentDto);

    Department merge(DepartmentDto dto, @MappingTarget Department entity);
}
