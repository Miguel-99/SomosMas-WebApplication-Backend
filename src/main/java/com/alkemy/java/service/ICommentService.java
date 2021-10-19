package com.alkemy.java.service;

import com.alkemy.java.dto.CommentDto;

public interface ICommentService {

    void update(Long id, CommentDto commentDto, String token);

}
