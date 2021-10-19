package com.alkemy.java.service;

import com.alkemy.java.dto.*;
import com.alkemy.java.exception.RemovedException;
import com.alkemy.java.model.Organization;
import javassist.NotFoundException;


public interface IOrganizationService {
    public void setContactFields(ContactFieldsDto contactFieldsDto) throws NotFoundException;
    OrganizationDto findById(Long id) throws NotFoundException;
    OrganizationResponseDto createOrganization(OrganizationRequestDto org) throws Exception;

}
