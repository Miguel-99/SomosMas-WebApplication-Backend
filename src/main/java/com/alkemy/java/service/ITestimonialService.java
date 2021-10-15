package com.alkemy.java.service;

import com.alkemy.java.dto.TestimonialDto;
import com.alkemy.java.dto.TestimonialResponseDto;


import com.alkemy.java.dto.TestimonialDto;

public interface ITestimonialService {

    TestimonialResponseDto createTestimonial (TestimonialDto testimonialDto);
    void deleteById(Long id);

    TestimonialDto updateTestimonial(Long id, TestimonialDto testimonialDto);

}
