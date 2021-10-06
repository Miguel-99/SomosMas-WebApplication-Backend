package com.alkemy.java.service;

import com.alkemy.java.dto.UserDto;
import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;

public interface IUserService {

     UserDtoResponse registerUser(UserDtoRequest user);
     UserDto updateUser(Long userId, UserDto userDto);
}
