package com.panda.mapper;

import com.panda.dto.HumanDto;import com.panda.model.Human;import com.panda.dto.HumanDto;
import com.panda.model.Human;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HumanMapper {

    HumanDto toDto(Human human);

    Human toEntity(HumanDto humanDto);

    Human merge(HumanDto dto, @MappingTarget Human entity);
}
