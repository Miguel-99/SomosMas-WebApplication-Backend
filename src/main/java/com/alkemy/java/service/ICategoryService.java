/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.java.service;

import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.dto.CategoryResponseDto;

/**
 *
 * @author Mariela
 */

public interface ICategoryService {
    
    CategoryResponseDto createCategory (CategoryRequestDto categoryRequest);
    void deleteCategory(Long id);
}
