package com.example.nrs.service;

import com.example.nrs.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUser();


}
