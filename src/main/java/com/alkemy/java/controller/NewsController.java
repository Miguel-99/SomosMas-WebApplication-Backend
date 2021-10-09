package com.alkemy.java.controller;

import com.alkemy.java.dto.NewsRequestDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;

    @PostMapping
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsRequestDto newsRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);

        return new ResponseEntity<>(newsService.createNews(newsRequestDto),HttpStatus.CREATED);
    }

}
