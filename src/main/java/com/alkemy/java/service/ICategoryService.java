/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.java.service;

import com.alkemy.java.dto.CategoryListRespDto;
import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.model.Category;
import javassist.NotFoundException;

import java.util.List;

/**
 *
 * @author Mariela
 */

public interface ICategoryService {


    CategoryResponseDto getCategoryById(Long categoryId) throws NotFoundException;

    CategoryResponseDto createCategory (CategoryRequestDto categoryRequest);

    CategoryResponseDto updateCategory(CategoryResponseDto dto, Long id);




    List<Category> findAllCategories();

}
