package com.alkemy.java.controller;

import com.alkemy.java.model.User;
import com.alkemy.java.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Value("{error.user.error}")
    private String error;

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> delete(@PathVariable Long idUser) {

        User user;

        try {
            user = userService.delete(idUser);
        } catch (Exception ex) {
            throw new RuntimeException(error);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }


}