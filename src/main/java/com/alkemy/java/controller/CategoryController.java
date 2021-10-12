
package com.alkemy.java.controller;

import com.alkemy.java.dto.CategoryListRespDto;
import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.dto.CategoryResponseDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.service.ICategoryService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
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

    @GetMapping("/")
    public ResponseEntity<List<CategoryListRespDto>> getAllCategories(){
        List<CategoryListRespDto> categories  = iCategoryService.findAllCategories()
                .stream()
                .map(CategoryListRespDto::new)
                .collect(Collectors.toList());
        return categories.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(categories) : ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryResponseDto categoryResponseDto, @PathVariable(name = "id") long id) {
        try {
            return new ResponseEntity<>(iCategoryService.updateCategory(categoryResponseDto,id), HttpStatus.OK);
        }catch(ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long categoryId) throws NotFoundException {

        return ResponseEntity.ok(iCategoryService.getCategoryById(categoryId));
    }

}
