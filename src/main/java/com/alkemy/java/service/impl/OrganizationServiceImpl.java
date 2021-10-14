package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ContactFieldsDto;
import com.alkemy.java.dto.SlidesDto;
import com.alkemy.java.dto.*;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.exception.Exception;
import com.alkemy.java.exception.RemovedException;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Category;
import com.alkemy.java.model.News;
import com.alkemy.java.model.Organization;

import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.IFileService;
import com.alkemy.java.service.IOrganizationService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.dto.OrganizationDto;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.service.IOrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    SlideRepository slideRepository;

    @Value("error.organization.dont.exist")
    private String errorOrganizationDontExist;

    @Value("error.organization.eliminated")
    private String errorOrganizationEliminated;

    @Value("error.organization.id.not.found")
    private String idNotFoundMessage;

    @Value("error.email.registered")
    private String errorPath;

    @Autowired
    private IFileService fileService;


    @Override
    public OrganizationDto findById(Long id) throws NotFoundException {

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


    @Override
    public OrganizationResponseDto createOrganization(OrganizationRequestDto request) throws Exception {

        if (organizationRepository.findByEmail(request.getEmail())!= null)
            throw new RuntimeException(messageSource.getMessage(errorPath, null, Locale.getDefault()));

        Organization updatedOrganization =
                OrganizationRequestDto.dtoToOrg(request);

        Organization org = organizationRepository.save(updatedOrganization);

        return OrganizationResponseDto.orgToDto(org);

    }

}
