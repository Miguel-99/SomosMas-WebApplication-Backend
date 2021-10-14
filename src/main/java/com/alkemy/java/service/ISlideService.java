
package com.alkemy.java.service;

import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.dto.SlideResponseDto;


public interface ISlideService {
    
    SlideResponseDto createSlide(SlideRequestDto slideRequest);
}
