package com.alkemy.java.service.impl;

import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.model.Organization;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.service.IOrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto findById(Long id) {
        boolean organizationExists = organizationRepository.existsById(id);
        if (!organizationExists)
            return null;

        ModelMapper modelMapper = new ModelMapper();
        Organization organization = organizationRepository.getOne(id);
        return modelMapper.map(organization, OrganizationDto.class);
    }

}