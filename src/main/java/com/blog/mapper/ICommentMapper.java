package com.blog.mapper;


import com.blog.dto.request.CommentSaveRequestDto;
import com.blog.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentMapper {

    ICommentMapper INSTANCE= Mappers.getMapper(ICommentMapper.class);

    Comment dtoToComment(CommentSaveRequestDto dto);


}
