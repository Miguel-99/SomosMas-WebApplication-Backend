package com.alkemy.java.service;

import com.alkemy.java.dto.UserDtoList;
import com.alkemy.java.dto.UserDto;
import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;
import com.alkemy.java.model.User;

import java.util.NoSuchElementException;

import java.util.List;

public interface IUserService {

     UserDtoResponse registerUser(UserDtoRequest user);
     UserDto updateUser(Long userId, UserDto userDto);
     boolean validedRole(Long id, String token);
     UserDtoResponse getUserInformation(Long id, String token);

     public User delete(Long idUser) throws NoSuchElementException;

     public User findById(Long idUser);

     List<UserDtoList> findAllUsers();
}
