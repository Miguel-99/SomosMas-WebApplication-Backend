package com.alkemy.java.controller;

import com.alkemy.java.dto.NewsDtoResponse;
import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.service.INewsService;
import java.util.Locale;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;
    
    @Autowired
    MessageSource messageSource;
    
    @Value("success.deleted")
    String messageDeleted;
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(messageSource.getMessage(messageDeleted, null, Locale.getDefault()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDtoResponse> getNewsById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(newsService.findById(id));
    }


}
