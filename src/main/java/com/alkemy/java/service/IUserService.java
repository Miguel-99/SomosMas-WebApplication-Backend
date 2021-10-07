package com.alkemy.java.service;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;
import com.alkemy.java.model.User;

public interface IUserService {

     UserDtoResponse registerUser(UserDtoRequest user);

     public User delete(Long idUser);

     public User findById(Long idUser);

}
