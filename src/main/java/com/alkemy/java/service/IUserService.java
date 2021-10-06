package com.alkemy.java.service;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.UserDtoResponse;

public interface IUserService {

     UserDtoResponse registerUser(UserDtoRequest user);
     boolean validedRole(Long id, String token);
}
