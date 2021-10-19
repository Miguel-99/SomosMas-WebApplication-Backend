package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.exception.ForbiddenException;
import com.alkemy.java.model.Comment;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.service.ICommentService;
import com.alkemy.java.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void update(Long id, CommentDto commentDto, String token) {
        Comment comment;
        Long userId;

        try{
            comment = commentRepository.getById(id);
            userId = comment.getUser().getId();
        } catch (Exception e){
            throw new BadRequestException(messageSource.getMessage("error.comment.invalid.id",null, Locale.getDefault()));
        }
        if(!userService.validedRole(userId,token)) throw new ForbiddenException(messageSource.getMessage("error.user.forbidden",null,Locale.getDefault()));

        comment.setBody(commentDto.getBody());
        comment.setLastUpdate(new Date());
        commentRepository.save(comment);
    }

}
