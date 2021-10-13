package com.alkemy.java.service;

import com.alkemy.java.dto.SlideDto;
import com.alkemy.java.dto.SlideResponseDto;

import java.util.List;

public interface ISlideService {

    List<SlideResponseDto> getAllSlide();
    void delete(Long id);
    SlideDto updateSlide (Long id, SlideDto slideDto);
}
