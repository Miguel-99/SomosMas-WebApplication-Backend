package com.alkemy.java.controller;

import com.alkemy.java.service.ITestimonialService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {


    @Autowired
    private ITestimonialService testimonialService;

    @Autowired
    private MessageSource messageSource;

    @Value("success.deleted")
    private String successfullyDeleted;

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
}
