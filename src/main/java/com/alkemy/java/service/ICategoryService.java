package com.alkemy.java.service;

import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.model.Category;
import javassist.NotFoundException;

public interface ICategoryService {
    CategoryResponseDto getCategoryById(Long categoryId) throws NotFoundException;

    CategoryResponseDto updateCategory(CategoryResponseDto dto, Long id);

}
