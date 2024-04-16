package com.example.springminiproject.mapper;

import com.example.springminiproject.model.User;
import com.example.springminiproject.model.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponse toResponse(User user);
}
