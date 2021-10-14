package com.alkemy.java.controller;

import com.alkemy.java.dto.ContactListDto;
import com.alkemy.java.dto.ContactResponseDto;
import com.alkemy.java.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @Autowired
    private MessageSource messageSource;

    @Value("success.get")
    private String successGet;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllContacts(){
        List<ContactListDto> contacts = contactService.getAllContacts();

        if (contacts.isEmpty())
            return new ResponseEntity<>(messageSource.getMessage(successGet, null, Locale.getDefault()), HttpStatus.OK);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
