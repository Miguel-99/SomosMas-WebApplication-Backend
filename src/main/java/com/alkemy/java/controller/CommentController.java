package com.alkemy.java.controller;


import com.alkemy.java.dto.CommentDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.exception.ForbiddenException;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.model.Comment;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.service.ICommentService;
import com.alkemy.java.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messageSource;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id,
                                           @Valid @RequestBody CommentDto commentDto,
                                           @RequestHeader("Authorization") String token,
                                           BindingResult bindingResult){
        Long userId;
        try{
            userId = commentRepository.getById(id).getUser().getId();
        } catch (Exception e){
            throw new BadRequestException(messageSource.getMessage("error.comment.invalid.id",null,Locale.getDefault()));
        }
        if(!userService.validedRole(userId,token)) throw new ForbiddenException(messageSource.getMessage("error.user.forbidden",null,Locale.getDefault()));
        if(bindingResult.hasErrors()) throw new InvalidDataException(bindingResult);

        commentService.update(id, commentDto);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

}
