
package com.alkemy.java.service;

import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.dto.SlideResponseCreateDto;
import com.alkemy.java.dto.SlideResponseDto;
import java.util.List;



public interface ISlideService {
    
    SlideResponseCreateDto createSlide(SlideRequestDto slideRequest);

    List<SlideResponseDto> getAllSlide();

    SlideResponseDto getById(Long id);

    void delete(Long id);

}
