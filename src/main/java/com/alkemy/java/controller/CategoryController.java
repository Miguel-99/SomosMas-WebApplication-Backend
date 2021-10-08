
package com.alkemy.java.controller;

import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ICategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Mariela
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    @PostMapping
    public ResponseEntity <?> createCategory (@Valid @RequestBody (required = true) CategoryRequestDto categoryRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);

            return new ResponseEntity<>(iCategoryService.createCategory(categoryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updatePost(@Valid @RequestBody CategoryResponseDto categoryResponseDto, @PathVariable(name = "id") long id) {
        CategoryResponseDto categoryResponse = categoryService.updateCategory(categoryResponseDto, id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }
}
