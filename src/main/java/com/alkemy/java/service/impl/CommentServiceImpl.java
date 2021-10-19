package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentDto;
import com.alkemy.java.model.Comment;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void update(Long id, CommentDto commentDto) {

        Comment comment = commentRepository.getById(id);
        comment.setBody(commentDto.getBody());
        comment.setLastUpdate(new Date());
        commentRepository.save(comment);
    }

}
