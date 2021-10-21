package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentRequestDto;
import com.alkemy.java.dto.CommentResponseDto;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.model.Comment;
import com.alkemy.java.model.News;
import com.alkemy.java.model.User;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.repository.UserRepository;
import com.alkemy.java.service.ICommentService;
import com.alkemy.java.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Value("error.member.idNotFound")
    private String messageNotFound;

    @Autowired
    private MessageSource messageSource;

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, String token){

        News news = newsRepository.findById(commentRequestDto.getNewsID()).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage(messageNotFound, null, Locale.getDefault())));

        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email);

        Comment comment = CommentRequestDto.dtoToComment(commentRequestDto);
        comment.setUser(user);
        comment.setNews(news);

        Comment finalComment = commentRepository.save(comment);
        return CommentResponseDto.commentToDto(finalComment);
    }

}
