package com.alkemy.java.service;

import com.alkemy.java.dto.SlideDto;
import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Slide;

import java.util.List;

public interface ISlideService {

    List<SlideResponseDto> getAllSlide();

    SlideResponseDto getById(Long id) throws ResourceNotFoundException;

    void delete(Long id);

    SlideDto updateSlide (Long id, SlideDto slideDto);
}
