package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentDto;
import com.alkemy.java.exception.BadRequestException;
import com.alkemy.java.exception.ForbiddenException;
import com.alkemy.java.model.Comment;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.service.ICommentService;
import com.alkemy.java.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ModelMapper mapper;


    @Override
    public void update(Long id, CommentDto commentDto, String token) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow( () -> new BadRequestException(messageSource.getMessage("error.comment.invalid.id",null, Locale.getDefault())));

        if(!userService.validedRole(comment.getUser().getId(),token)) throw new ForbiddenException(messageSource.getMessage("error.user.forbidden",null,Locale.getDefault()));

        comment.setBody(commentDto.getBody());
        comment.setLastUpdate(new Date());
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByIdNews(Long id) {
        return commentRepository.getCommentsByIdNews(id);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<CommentDto> dtoComments = new ArrayList<>();
        commentRepository.findAllByOrder().forEach(comment ->dtoComments.add(mapper.map(comment,CommentDto.class)));

        return dtoComments;

    }

    @Override
    public void deleteComment(Long id, String token) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow( () -> new BadRequestException(messageSource.getMessage("error.comment.invalid.id",null, Locale.getDefault())));

        if(!userService.validedRole(comment.getUser().getId(),token))
            throw new ForbiddenException(messageSource.getMessage("error.user.forbidden",null,Locale.getDefault()));

        commentRepository.delete(comment);
    }
}
