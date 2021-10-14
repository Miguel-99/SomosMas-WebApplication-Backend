package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.dto.SlidesDto;
import com.alkemy.java.model.Organization;

import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.IOrganizationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.dto.OrganizationDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public OrganizationDto findById(Long id)throws NotFoundException {

        OrganizationDto response = modelMapper.map(existsVerification(id), OrganizationDto.class);

        List<SlidesDto> slides = slideRepository.findAllByOrganizationId(id)
                .stream()
                .map(SlidesDto::new)
                .collect(Collectors.toList());

        response.setSlides(slides);

        return response;
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
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("error.organization.dont.exist", null, Locale.getDefault())));
    }

}