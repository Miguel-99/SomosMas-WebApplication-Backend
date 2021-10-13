package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ContactRequestDto;
import com.alkemy.java.dto.ContactResponseDto;
import com.alkemy.java.model.Contact;
import com.alkemy.java.repository.ContactRepository;
import com.alkemy.java.service.IContactService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;


@Service
@Transactional
public class ContactServiceImpl implements IContactService {
    @Autowired
    ModelMapper mapper;
    
    @Autowired
    ContactRepository contactRepository;
           
    

    @Override
    public ContactResponseDto createContact(ContactRequestDto contactRequest) {
        
        Contact contact = mapToEntity (contactRequest);
        contact.setCreatedDate(new Date());
        contact.setUpdateDate(new Date());
        
        contactRepository.save(contact);
        
        return mapToDto (contact); 
    }
    
        private ContactResponseDto mapToDto(Contact contact) {
        return mapper.map(contact, ContactResponseDto.class);
    }

    private Contact mapToEntity(ContactRequestDto contactRequest) {

        return mapper.map(contactRequest,Contact.class );
    }
    
    
    
}
