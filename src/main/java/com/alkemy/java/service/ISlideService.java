
package com.alkemy.java.service;


import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.dto.SlideResponseCreateDto;
import com.alkemy.java.dto.SlideResponseDto;
import com.alkemy.java.dto.SlideDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;



public interface ISlideService {
    
    SlideResponseCreateDto createSlide(SlideRequestDto slideRequest, MultipartFile file) throws Exception;

    List<SlideResponseDto> getAllSlide();

    SlideResponseDto getById(Long id);

    void delete(Long id);

    SlideDto updateSlide (Long id, SlideDto slideDto);
}
