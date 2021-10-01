package com.alkemy.java.service.impl;

import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.model.Contact;
import com.alkemy.java.repository.ContactRepository;
import com.alkemy.java.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContactServiceImpl implements IContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public void create(Contact contact) {
        if (contactRepository.existsById(contact.getId())){
            throw new BadRequestException("the contact already exists");
        }
        contactRepository.save(contact);
    }
    
}
