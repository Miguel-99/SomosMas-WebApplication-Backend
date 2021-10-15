package com.alkemy.java.service.impl;

import com.alkemy.java.dto.TestimonialDto;
import com.alkemy.java.dto.TestimonialResponseDto;
import com.alkemy.java.model.Testimonial;
import com.alkemy.java.repository.TestimonialRepository;
import com.alkemy.java.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import com.alkemy.java.exception.ResourceNotFoundException;
import java.util.Date;
import org.springframework.stereotype.Service;

import java.util.Locale;
import org.modelmapper.ModelMapper;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private ModelMapper mapper;

    @Value("error.testimonial.id.not.found")
    private String idNotFoundMessage;
    


    @Override
    public void deleteById(Long id) {
        Testimonial slide = testimonialRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(messageSource.getMessage
                        (idNotFoundMessage, null, Locale.getDefault())));
                   testimonialRepository.delete(slide);
    }

    @Override
    public TestimonialResponseDto createTestimonial(TestimonialDto testimonialDto) {
        
        Testimonial testimonial = mapper.map(testimonialDto,Testimonial.class);
        
        testimonial.setCreateDate(new Date());
        testimonial.setUpdateDate(new Date());
        
        testimonialRepository.save(testimonial);
        
        return mapper.map(testimonial,TestimonialResponseDto.class);
    }
    
    @Override    
    public TestimonialDto updateTestimonial(Long id, TestimonialDto testimonialDto) {
        Testimonial updatedTestimonial = testimonialRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(idNotFoundMessage, null, Locale.getDefault())));

        updatedTestimonial.setName(testimonialDto.getName());
        updatedTestimonial.setImage(testimonialDto.getImage());
        updatedTestimonial.setContent(testimonialDto.getContent());

        testimonialRepository.save(updatedTestimonial);
        return TestimonialDto.testimonialToDto(updatedTestimonial);


    }
}
