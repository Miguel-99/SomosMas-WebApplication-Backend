package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Category;
import com.alkemy.java.repository.CategoryRepository;
import com.alkemy.java.service.ICategoryService;
import com.alkemy.java.service.IFileService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
@PropertySource("classpath:messages/messages.properties")
public class CategoryServiceImpl implements ICategoryService {

    @Value("error.category.notfound")
    private String notFoundMessage;

    @Value("error.category.id.not.found")
    private String idNotFoundMessage;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    IFileService fileService;

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) throws NotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage(notFoundMessage, null, Locale.getDefault())));
        return CategoryResponseDto.buildResponse(category);
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryResponseDto categoryResponseDto, Long id) {

        Category updatedCategory = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(messageSource.getMessage
                        (idNotFoundMessage, null, Locale.getDefault())));

        updatedCategory.setName(categoryResponseDto.getName());
        updatedCategory.setDescription(categoryResponseDto.getDescription());
        updatedCategory.setImage(categoryResponseDto.getImage());
        updatedCategory.setLastUpdate(new Date());
        categoryRepository.save(updatedCategory);

        return CategoryResponseDto.buildResponse(updatedCategory);
    }


}



