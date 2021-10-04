package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.exception.RemovedException;
import com.alkemy.java.model.Organization;

import com.alkemy.java.service.IOrganizationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.service.IOrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Value("error.organization.dont.exist")
    private String errorOrganizationDontExist;

    @Value("error.organization.eliminated")
    private String errorOrganizationEliminated;

    @Override
    public OrganizationDto findById(Long id) {
        boolean organizationExists = organizationRepository.existsById(id);
        if (!organizationExists)
            return null;

        ModelMapper modelMapper = new ModelMapper();
        Organization organization = organizationRepository.getOne(id);
        return modelMapper.map(organization, OrganizationDto.class);
    }
    @Override
    public void setContactFields(ContactFieldsDto contactFieldsDto) throws NotFoundException, RemovedException {

        removedVerification(contactFieldsDto.getId());

        organizationRepository.setContactInfoById(contactFieldsDto.getLinkedinUrl(),
                contactFieldsDto.getFacebookUrl(),
                contactFieldsDto.getInstagramUrl(),
                contactFieldsDto.getId());
    }
    private void removedVerification(Long idOrg) throws NotFoundException, RemovedException {

        Organization organization =  organizationRepository.findById(idOrg)
                .orElseThrow(() -> new NotFoundException(errorOrganizationDontExist));

        if(organization.getDeleted()){
            throw new RemovedException(errorOrganizationEliminated);
        }
    }

}