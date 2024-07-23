package com.panda.mapper;

import com.panda.dto.EmployeeDto;import com.panda.dto.EmployeeDto;
import com.panda.model.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);

    Employee merge(EmployeeDto dto, @MappingTarget Employee entity);
}
