package com.alkemy.java.service.impl;

import com.alkemy.java.model.Testimonial;
import com.alkemy.java.repository.TestimonialRepository;
import com.alkemy.java.service.ITestimonialService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import com.alkemy.java.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private MessageSource messageSource;

    @Value("error.testimonial.id.not.found")
    private String idNotFoundMessage;

    @Override
    public void deleteById(Long id) {
        Testimonial slide = testimonialRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(messageSource.getMessage
                        (idNotFoundMessage, null, Locale.getDefault())));
                   testimonialRepository.delete(slide);
    }
}
