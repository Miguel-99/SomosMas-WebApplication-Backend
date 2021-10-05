package com.alkemy.java.controller;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.dto.AuthenticationRequestDto;
import com.alkemy.java.dto.AuthenticationResponseDto;
import com.alkemy.java.model.User;
import com.alkemy.java.model.UserDetail;
import com.alkemy.java.service.impl.UserDetailsServiceImpl;
import com.alkemy.java.service.impl.UserServiceImpl;
import com.alkemy.java.util.JwtUtil;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @Value("error.username.password.incorrect")
    private String errorUsernamePasswordIncorrect;

    @PostMapping("/authentication")
    public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception(errorUsernamePasswordIncorrect, e);
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
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDtoRequest userDtoRequest) {

        return new ResponseEntity<>(userService.registerUser(userDtoRequest), HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public boolean listUsers (@PathVariable Long id, @AuthenticationPrincipal UserDetail user){
        if (userService.validedRole(id,user)){
            return true;
        }
        return false;
    }

}
