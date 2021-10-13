package com.alkemy.java.controller;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.dto.OrganizationRequestDto;
import com.alkemy.java.model.Organization;
import com.alkemy.java.service.IOrganizationService;
import com.alkemy.java.service.impl.OrganizationServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    IOrganizationService organizationService;


    @PatchMapping("/contact")
    public ResponseEntity setContactInfo(@RequestBody ContactFieldsDto contactFieldsDto) throws NotFoundException {
        organizationService.setContactFields(contactFieldsDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/public/{organizationId}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable Long organizationId) throws NotFoundException {
        return ResponseEntity.ok(organizationService.findById(organizationId));
    }

    @PostMapping("/public")
    public ResponseEntity<?> createOrganization(@Valid @RequestBody OrganizationRequestDto request){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.createOrganization(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
