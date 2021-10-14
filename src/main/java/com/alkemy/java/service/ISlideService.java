
package com.alkemy.java.service;

import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.dto.SlideResponseDto;
import java.util.List;


public interface ISlideService {
    
    SlideResponseDto createSlide(SlideRequestDto slideRequest);

    List<SlideResponseDto> getAllSlide();
    
    void delete(Long id);

}
