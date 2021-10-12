package com.alkemy.java.service;

import com.alkemy.java.dto.SlideResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISlideService {

    List<SlideResponseDto> getAllSlide();
}
