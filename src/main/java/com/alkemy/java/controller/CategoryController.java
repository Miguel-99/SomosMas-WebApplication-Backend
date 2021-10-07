package com.alkemy.java.controller;

import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.model.Category;
import com.alkemy.java.service.ICategoryService;
import com.alkemy.java.service.impl.CategoryServiceImpl;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long categoryId) throws NotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updatePost(@Valid @RequestBody CategoryResponseDto categoryResponseDto, @PathVariable(name = "id") long id) {
        CategoryResponseDto categoryResponse = categoryService.updateCategory(categoryResponseDto, id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

}
