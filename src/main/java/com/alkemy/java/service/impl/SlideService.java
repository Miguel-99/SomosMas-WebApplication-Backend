package com.alkemy.java.service.impl;

import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.model.Slide;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.ISlideService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlideService implements ISlideService {

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SlideResponseDto> getAllSlide(){
        List<Slide> slides = slideRepository.findAll();
        return slides.stream().map(slide -> modelMapper.map(slide, SlideResponseDto.class)).collect(Collectors.toList());
    }
}
