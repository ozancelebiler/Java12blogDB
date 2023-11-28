package com.blog.mapper;



import com.blog.dto.request.UserRegisterRequestDto;
import com.blog.dto.request.UserSaveRequestDto;
import com.blog.dto.response.UserLoginResponseDto;
import com.blog.dto.response.UserRegisterResponseDto;
import com.blog.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User dtoToUser(UserRegisterRequestDto dto);

    UserRegisterResponseDto userToDto(User user);

    UserLoginResponseDto userToLoginDto(User user);

    User saveRequestDtoToUser(UserSaveRequestDto dto);

    UserSaveRequestDto userToSaveRequestDto(User user);



}
