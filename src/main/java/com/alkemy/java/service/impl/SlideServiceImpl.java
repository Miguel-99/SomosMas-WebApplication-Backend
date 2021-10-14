package com.alkemy.java.service.impl;

import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.model.Organization;
import com.alkemy.java.model.Slide;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.ISlideService;
import java.util.Date;
import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements ISlideService {

    @Autowired
    SlideRepository slideRepository;
    
    @Autowired
    OrganizationRepository organizationRepository;
    
    @Autowired
    ModelMapper mapper;
    
    @Autowired
    MessageSource messageSource;
    
    @Value("error.organization.dont.exist")
    private String errorOrganizationDontExist;

    @Override
    public SlideResponseDto createSlide(SlideRequestDto slideRequest) {
             
        if (slideRequest.getNumberOrder() == null || slideRepository.findByNumberOrder(slideRequest.getNumberOrder()) != null ) {
            
            slideRequest.setNumberOrder(slideRepository.maxOrder()+1);
        }
           
        if (!organizationRepository.existsById(slideRequest.getOrganizationDto().getId())) {
            throw new BadRequestException (messageSource.getMessage(errorOrganizationDontExist, null, Locale.getDefault()));
        }
        
        Slide slide = mapToEntity(slideRequest);
        
        slide.setCreateDate(new Date ());
        slide.setLastUpdate(new Date ());
        slide.setNumberOrder(slideRequest.getNumberOrder());
        slide.setOrganizationId(mapper.map(slideRequest.getOrganizationDto(), Organization.class));
        
        slideRepository.save(slide);
        
        return mapToDto(slide);
        
    }

    
     private SlideResponseDto mapToDto(Slide slide) {
        return mapper.map(slide, SlideResponseDto.class);
    }

    private Slide mapToEntity(SlideRequestDto categoryRequest) {

        return mapper.map(categoryRequest,Slide.class );
    }
}
