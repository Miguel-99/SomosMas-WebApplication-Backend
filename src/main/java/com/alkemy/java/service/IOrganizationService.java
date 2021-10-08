package com.alkemy.java.service;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.exception.RemovedException;
import javassist.NotFoundException;


public interface IOrganizationService {
    public void setContactFields(ContactFieldsDto contactFieldsDto) throws NotFoundException;
    OrganizationDto findById(Long id) throws NotFoundException;
}
