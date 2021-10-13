package com.alkemy.java.controller;

import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private ISlideService slideService;

    @Value("success.get")
    private String successGet;

    @Autowired
    private MessageSource messageSource;

    @Value("success.deleted")
    private String deletedMessage;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllSlides(){

        List<SlideResponseDto> slides = slideService.getAllSlide();

        if(slides.isEmpty()){
            String message = messageSource.getMessage(successGet, null, Locale.getDefault());
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(slideService.getAllSlide(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        slideService.delete(id);
        return new ResponseEntity<>(messageSource.getMessage(deletedMessage, null, Locale.getDefault()), HttpStatus.OK);
    }
}
