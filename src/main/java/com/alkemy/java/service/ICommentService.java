package com.alkemy.java.service;

import com.alkemy.java.dto.CommentDto;
import com.alkemy.java.model.Comment;

import java.util.List;

public interface ICommentService {

    void update(Long id, CommentDto commentDto, String token);

    List<Comment> getCommentsByIdNews(Long id);

    List<CommentDto> getAllComments();
}
