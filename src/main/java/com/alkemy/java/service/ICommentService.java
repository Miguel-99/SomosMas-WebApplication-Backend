package com.alkemy.java.service;

import com.alkemy.java.dto.CommentResponseDto;
import com.alkemy.java.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {

    List<Comment> getCommentsByIdNews(Long id);
}
