package com.alkemy.java.controller;

import com.alkemy.java.dto.*;
import com.alkemy.java.service.IUserService;
import com.alkemy.java.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;


import javax.validation.Valid;
import java.util.Locale;

@Api(value= "Authentication Controller")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    MessageSource messageSource;

    @Value("error.empty.register")
    private String errorUsernamePasswordIncorrect;
    
    
    
    @ApiOperation("User Authentication")
    @ApiResponses({ 
               @ApiResponse(code = 202, message = "Accepted"),
               @ApiResponse(code = 400, message = "Bad Request"),
               @ApiResponse(code = 401, message = "Unauthorized"),
               @ApiResponse(code = 404, message = "Not Found"),
               @ApiResponse(code = 500, message = "Internal server error")
    })
    
    @PostMapping("/authentication")
    public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception(messageSource.getMessage(errorUsernamePasswordIncorrect, null, Locale.getDefault()), e);
        }
        try {
            UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            String jwt = jwtUtil.generateToken(user);
            return new ResponseEntity<>(new AuthenticationResponseDto(jwt), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    
    @ApiOperation("User Registration")
    @ApiResponses({ 
               @ApiResponse(code = 200, message = "OK"), 
               @ApiResponse(code = 400, message = "Bad Request"),
               @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDtoRequest userDtoRequest) {

        UserDtoResponse response =userService.registerUser(userDtoRequest);


        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDtoRequest.getEmail(), userDtoRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(userDtoRequest.getEmail());

        String token = jwtUtil.generateToken(userDetails);


        return ResponseEntity.ok(new JWTAuthResponse(token,response));

    }
    
    @ApiOperation("Get User Details")
    @ApiResponses({ 
               @ApiResponse(code = 200, message = "OK"),
               @ApiResponse(code = 401, message= "Unauthorized"),
               @ApiResponse(code = 403, message = "Forbidden Access"),
               @ApiResponse(code = 404, message = "Not Found"),
               @ApiResponse(code = 500, message = "Internal server error")
    })
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserInformation (@PathVariable Long id, @RequestHeader(name = "Authorization", required = true) String token){
        return new ResponseEntity<>(userService.getUserInformation(id, token), HttpStatus.OK);
    }
}
