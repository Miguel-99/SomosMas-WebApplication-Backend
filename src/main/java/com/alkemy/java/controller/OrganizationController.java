package com.alkemy.java.controller;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.exception.RemovedException;
import com.alkemy.java.service.impl.OrganizationServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationServiceImpl organizationService;


    @PutMapping("/contact")
    public ResponseEntity setContactInfo(@RequestBody ContactFieldsDto contactFieldsDto) throws NotFoundException, RemovedException {
        organizationService.setContactFields(contactFieldsDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
