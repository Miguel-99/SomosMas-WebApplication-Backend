package com.alkemy.java.service;


import com.alkemy.java.dto.TestimonialDto;

public interface ITestimonialService {

    void deleteById(Long id);

    TestimonialDto updateTestimonial(Long id, TestimonialDto testimonialDto);

}
