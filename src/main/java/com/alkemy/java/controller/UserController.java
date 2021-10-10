package com.alkemy.java.controller;

import com.alkemy.java.model.User;
import com.alkemy.java.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    MessageSource messageSource;

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> delete(@PathVariable Long idUser) {
        User user = userService.delete(idUser);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


}