package com.alkemy.java.controller;

import com.alkemy.java.dto.TestimonialDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {


    @Autowired
    private ITestimonialService testimonialService;

    @Autowired
    private MessageSource messageSource;

    @Value("success.deleted")
    private String successfullyDeleted;
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping 
    public ResponseEntity <?> createTestomonial (@Valid @RequestBody TestimonialDto testinonialRequest,BindingResult bindingResult){
           
        if (bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);
        
                
        return new ResponseEntity <>(testimonialService.createTestimonial(testinonialRequest),HttpStatus.CREATED);
        
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTestimonialById(@PathVariable Long id) {
        try {
            testimonialService.deleteById(id);

            return new ResponseEntity<>(messageSource.getMessage
                    (successfullyDeleted, null, Locale.getDefault()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody TestimonialDto testimonialDto) {
        TestimonialDto testimonial = testimonialService.updateTestimonial(id, testimonialDto);
        return new ResponseEntity<>(testimonial, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getTestimonials (@PageableDefault(size = 10) Pageable page, HttpServletRequest request){
    return new ResponseEntity<>(testimonialService.findAll(page, request),HttpStatus.OK);
    }
}
