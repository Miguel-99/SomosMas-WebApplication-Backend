package com.alkemy.java.service;


import com.alkemy.java.dto.NewsDtoResponse;
import com.alkemy.java.exception.ResourceNotFoundException;

public interface INewsService {
        
    void deleteNews(Long id);

    NewsDtoResponse findById(Long id) throws ResourceNotFoundException;

}
