
package com.alkemy.java.controller;

import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ICategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteCategory (@PathVariable Long id){
        iCategoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
