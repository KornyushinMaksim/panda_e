package com.panda.mapper;

import com.panda.dto.CommentDto;
import com.panda.model.Comment;
import com.panda.service.CommentService;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    Comment marge(CommentDto dto, @MappingTarget Comment entity);
}
