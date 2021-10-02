package com.alkemy.java.service.impl;

import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

}
