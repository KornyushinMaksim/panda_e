package com.panda.mapper;

import com.panda.dto.FileToDataBaseDto;import com.panda.dto.FileToDataBaseDto;
import com.panda.model.FileToDataBase;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FileToDataBaseMapper {

    FileToDataBaseDto toDto(FileToDataBase fileToDataBase);

    FileToDataBase toEntity(FileToDataBaseDto fileToDataBaseDto);
}
