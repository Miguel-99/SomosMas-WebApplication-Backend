package com.alkemy.java.controller;

import com.alkemy.java.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    ISlideService slideService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllSlides(){
        return new ResponseEntity<>(slideService.getAllSlide(), HttpStatus.OK);
    }
}
