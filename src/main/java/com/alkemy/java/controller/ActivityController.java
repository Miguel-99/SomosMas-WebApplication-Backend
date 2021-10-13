package com.alkemy.java.controller;


import com.alkemy.java.dto.ActivityDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createActivity(@Valid @RequestBody ActivityDto activityDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);

        activityService.createActivity(activityDto);

        return new ResponseEntity<>(activityDto, HttpStatus.CREATED);
    }

}
