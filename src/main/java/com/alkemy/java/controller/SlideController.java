
package com.alkemy.java.controller;

import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ISlideService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
public class SlideController {
    @Autowired
    ISlideService slideService;
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping  
    public ResponseEntity <?> createSlide (@Valid @RequestBody SlideRequestDto slideRequestDto, BindingResult bindingResult){
        
        if (bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);
        
        return new ResponseEntity<> (slideService.createSlide(slideRequestDto),HttpStatus.CREATED);
        
    }
}
