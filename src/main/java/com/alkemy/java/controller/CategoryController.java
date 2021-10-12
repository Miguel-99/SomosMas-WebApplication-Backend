
package com.alkemy.java.controller;

import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ICategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 *
 * @author Mariela
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    ICategoryService iCategoryService;

    @Autowired
    MessageSource messageSource;

    @Value("success.deleted")
    String messageDeleted;

    @PostMapping
    public ResponseEntity <?> createCategory (@Valid @RequestBody (required = true) CategoryRequestDto categoryRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);
        
            return new ResponseEntity<>(iCategoryService.createCategory(categoryRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteCategory (@PathVariable Long id){
        iCategoryService.deleteCategory(id);
        return new ResponseEntity<>(messageSource.getMessage(messageDeleted, null, Locale.getDefault()), HttpStatus.OK);
    }
}
