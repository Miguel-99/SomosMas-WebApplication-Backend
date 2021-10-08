package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.exception.RemovedException;
import com.alkemy.java.model.Organization;

import com.alkemy.java.service.IOrganizationService;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.service.IOrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private MessageSource messageSource;

    @Value("error.organization.dont.exist")
    private String errorOrganizationDontExist;

    @Value("error.organization.eliminated")
    private String errorOrganizationEliminated;

    @Override
    public OrganizationDto findById(Long id)throws NotFoundException {

        Organization organization = existsVerification(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(organization, OrganizationDto.class);
    }

    @Override
    public void setContactFields(ContactFieldsDto contactFieldsDto) throws NotFoundException {

        existsVerification(contactFieldsDto.getId());

        organizationRepository.setContactInfoById(contactFieldsDto.getLinkedinUrl(),
                contactFieldsDto.getFacebookUrl(),
                contactFieldsDto.getInstagramUrl(),
                contactFieldsDto.getId());
    }

    private Organization existsVerification(Long idOrg) throws NotFoundException {
        return organizationRepository.findById(idOrg)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage(errorOrganizationDontExist, null, Locale.getDefault())));
    }

}