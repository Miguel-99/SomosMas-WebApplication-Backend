package com.alkemy.java.service;

import com.alkemy.java.dto.TestimonialDto;
import com.alkemy.java.dto.TestimonialResponseDto;


public interface ITestimonialService {

    TestimonialResponseDto createTestimonial (TestimonialDto testimonialDto);
    void deleteById(Long id);

}
