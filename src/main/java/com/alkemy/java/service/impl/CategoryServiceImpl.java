/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Category;
import com.alkemy.java.repository.CategoryRepository;
import com.alkemy.java.service.ICategoryService;
import java.util.Date;
import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


/**
 *
 * @author Mariela
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    MessageSource messageSource;
    
    @Value ("error.service.category.badrequest")
    private String errorBadRequest;

    @Value ("error.service.category.does.not.exist")
    private String errorDoesNotExist;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequest) {
        
        Category category = categoryRepository.findByName(categoryRequest.getName());

        if (category != null) 
            throw new BadRequestException(messageSource.getMessage(errorBadRequest, null, Locale.getDefault()));
        
        category = mapToEntity(categoryRequest);
        category.setCreateDate(new Date());
        category.setLastUpdate(new Date());

        categoryRepository.save(category);
        
        return mapToDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow( () -> {
            throw new ResourceNotFoundException(messageSource.getMessage(errorDoesNotExist, null, Locale.getDefault()));
        }));
    }

    private CategoryResponseDto mapToDto(Category category) {
        return mapper.map(category, CategoryResponseDto.class);
    }

    private Category mapToEntity(CategoryRequestDto categoryRequest) {
        
        return mapper.map(categoryRequest,Category.class );
    }
}
