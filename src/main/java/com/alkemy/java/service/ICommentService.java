package com.alkemy.java.service;

import com.alkemy.java.dto.CommentRequestDto;
import com.alkemy.java.dto.CommentResponseDto;

public interface ICommentService {

    CommentResponseDto createComment(CommentRequestDto commentRequestDto, String token);
}
