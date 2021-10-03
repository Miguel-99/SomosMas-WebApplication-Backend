package com.alkemy.java.controller.exception;

import com.alkemy.java.dto.ErrorMessageDto;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto usernameNotFoundException(UsernameNotFoundException ex) {
        return new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), new Date(),"UsernameNotFoundException", ex.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessageDto.builder()
                .statusCode(404)
                .timestamp(new Date())
                .exception("NotFoundException")
                .message(exception.getMessage())
                .build());
    }
}