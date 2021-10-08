package com.alkemy.java.controller;

import com.alkemy.java.dto.UserDto;
import com.alkemy.java.service.IUserService;
import com.alkemy.java.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto user = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
