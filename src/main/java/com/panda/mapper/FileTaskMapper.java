package com.panda.mapper;

import com.panda.dto.FileTaskDto;
import com.panda.model.FileTask;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FileTaskMapper {

    FileTaskDto toDto(FileTask fileTask);

    FileTask toEntity(FileTaskDto fileTaskDto);

    FileTask marge(FileTaskDto dto, @MappingTarget FileTask entity);
}
