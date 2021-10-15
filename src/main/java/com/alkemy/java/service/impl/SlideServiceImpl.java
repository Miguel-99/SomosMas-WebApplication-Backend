package com.alkemy.java.service.impl;

import com.alkemy.java.dto.SlideDto;
import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Slide;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.ISlideService;
import com.amazonaws.services.kms.model.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class SlideServiceImpl implements ISlideService {

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MessageSource messageSource;

    @Value("error.user.notFoundID")
    private String idNotFound;

    @Value("error.slide.notFound")
    private String resourceNotFound;

    @Override
    public List<SlideResponseDto> getAllSlide() {
        List<Slide> slides = slideRepository.findAll();
        return slides.stream().map(slide -> modelMapper.map(slide, SlideResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Slide slide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFound, null, Locale.getDefault())));
        slideRepository.delete(slide);
    }

    @Override
    @Transactional(readOnly = true)
    public SlideResponseDto getById(Long id) {
        Slide slide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFound, null, Locale.getDefault())));
        return modelMapper.map(slide,SlideResponseDto.class);
    }

    public SlideDto updateSlide(Long id, SlideDto slideDto) {
        Slide updatedSlide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFound, null, Locale.getDefault())));

        updatedSlide.setImageUrl(slideDto.getImageUrl());
        updatedSlide.setText(slideDto.getText());
        updatedSlide.setNumberOrder(slideDto.getNumberOrder());
        updatedSlide.setOrganizationId(slideDto.getOrganization());

        slideRepository.save(updatedSlide);
        return SlideDto.slideToDto(updatedSlide);
    }
}
