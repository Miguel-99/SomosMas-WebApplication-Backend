
package com.alkemy.java.controller;

import com.alkemy.java.dto.SlideRequestDto;
import com.alkemy.java.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.java.dto.SlideResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private ISlideService slideService;
    
    @Value("success.get")
    private String successGet;

    @Autowired
    private MessageSource messageSource;
    


    @Value("success.deleted")
    private String deletedMessage;
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping  
    public ResponseEntity <?> createSlide (@RequestParam("file") MultipartFile file ,  @RequestParam ("slide") String resquest) throws Exception{
        
        ObjectMapper mapper = new ObjectMapper();
        SlideRequestDto slideRequestDto = mapper.readValue(resquest, SlideRequestDto.class);
        
        return new ResponseEntity<> (slideService.createSlide(slideRequestDto, file),HttpStatus.CREATED);
        
    }
    
   
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllSlides(){

        List<SlideResponseDto> slides = slideService.getAllSlide();

        if(slides.isEmpty()){
            String message = messageSource.getMessage(successGet, null, Locale.getDefault());
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(slideService.getAllSlide(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        slideService.delete(id);
        return new ResponseEntity<>(messageSource.getMessage(deletedMessage, null, Locale.getDefault()), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getSlide(@PathVariable Long id){
        SlideResponseDto slide = slideService.getById(id);

        return  ResponseEntity.status(HttpStatus.OK).body(slide);


    }

}
