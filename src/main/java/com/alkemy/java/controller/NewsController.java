package com.alkemy.java.controller;

import com.alkemy.java.dto.NewsDto;
import com.alkemy.java.dto.NewsResponseDto;
import com.alkemy.java.service.INewsService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.java.dto.NewsRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;
    
    @Autowired
    MessageSource messageSource;
    
    @Value("success.deleted")
    String messageDeleted;

    @PostMapping
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsRequestDto newsRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);

        return new ResponseEntity<>(newsService.createNews(newsRequestDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/:{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(messageSource.getMessage(messageDeleted, null, Locale.getDefault()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable("id") Long id, @Valid @RequestBody NewsDto newsDto) {
        NewsDto newsDtoResponse = newsService.updateNews(id, newsDto);
        return new ResponseEntity<>(newsDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.findNewsById(id));
    }
}
