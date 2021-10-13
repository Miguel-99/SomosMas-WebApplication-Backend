package com.alkemy.java.service;

import com.alkemy.java.dto.CategoryListRespDto;
import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.model.Category;
import javassist.NotFoundException;

import java.util.List;


public interface ICategoryService {


    CategoryResponseDto getCategoryById(Long categoryId) throws NotFoundException;
    CategoryResponseDto createCategory (CategoryRequestDto categoryRequest);
    void deleteCategory(Long id);
    CategoryResponseDto updateCategory(CategoryResponseDto dto, Long id);
    List<Category> findAllCategories();

}
