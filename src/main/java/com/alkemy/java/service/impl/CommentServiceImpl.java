package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentResponseDto;
import com.alkemy.java.model.Comment;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByIdNews(Long id) {
        return commentRepository.getCommentsByIdNews(id);
    }
}
