package com.alkemy.java.controller.exception;

import com.alkemy.java.dto.ErrorMessageDto;
import javassist.NotFoundException;
import com.alkemy.java.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Date;
import java.util.NoSuchElementException;

import static com.alkemy.java.util.ExceptionConstant.NOT_FOUND;
import static com.alkemy.java.util.ExceptionConstant.USERNAME_NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto usernameNotFoundException(UsernameNotFoundException ex) {
        return new ErrorMessageDto(new Date(), USERNAME_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessageDto.builder()
                .timestamp(new Date())
                .exception(NOT_FOUND)
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessageDto(new Date(),"ResourceNotFoundException", ex.getMessage());
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ErrorMessageDto noSuchElementException(NoSuchElementException ex) {
        return new ErrorMessageDto(new Date(), "NoSuchElementException", ex.getMessage());
    }

}