package com.alkemy.java.controller;

import com.alkemy.java.dto.NewsRequestDto;
import com.alkemy.java.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;

    @PostMapping
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsRequestDto newsRequestDto) {

        return new ResponseEntity<>(newsService.save(newsRequestDto),HttpStatus.CREATED);
    }

}
