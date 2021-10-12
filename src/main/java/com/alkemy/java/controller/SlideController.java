package com.alkemy.java.controller;

import com.alkemy.java.dto.RoleDto;
import com.alkemy.java.service.IRoleService;
import com.alkemy.java.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    ISlideService slideService;

    @GetMapping()
    public ResponseEntity<?> getAllSlides(){
        return new ResponseEntity<>(slideService.getAllSlide(), HttpStatus.OK);
    }

}
