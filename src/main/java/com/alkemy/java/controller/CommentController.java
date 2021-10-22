package com.alkemy.java.controller;

import com.alkemy.java.dto.CommentDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Value("${comment.list.empty}")
    private String commentListEmpty;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id,
                                           @Valid @RequestBody CommentDto commentDto,
                                           @RequestHeader("Authorization") String token,
                                           BindingResult bindingResult){

        if(bindingResult.hasErrors()) throw new InvalidDataException(bindingResult);

        commentService.update(id, commentDto, token);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<?> getComments(){

        return new ResponseEntity<>(commentService.getAllComments().isEmpty()?
                commentListEmpty:commentService.getAllComments(), HttpStatus.OK);
    }


}
