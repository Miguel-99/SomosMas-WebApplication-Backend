package com.alkemy.java.controller;

import com.alkemy.java.dto.CommentRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ICommentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequestDto commentRequestDto,
                                           @Parameter(hidden = true) BindingResult bindingResult,
                                           @RequestHeader(name = "Authorization", required = true) String token){
        if (bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);

        return new ResponseEntity <>(commentService.createComment(commentRequestDto, token), HttpStatus.CREATED);
    }
}
