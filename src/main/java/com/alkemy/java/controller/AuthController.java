package com.alkemy.java.controller;

import com.alkemy.java.dto.AuthenticationRequestDto;
import com.alkemy.java.dto.AuthenticationResponseDto;
import com.alkemy.java.dto.UserDto;
import com.alkemy.java.service.impl.UserDetailsServiceImpl;
import com.alkemy.java.service.impl.UserServiceImpl;
import com.alkemy.java.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        try{
            UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            String jwt = jwtUtil.generateToken(user);
            return new ResponseEntity<>(new AuthenticationResponseDto(jwt), HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.registerUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }


}
