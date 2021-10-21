package com.alkemy.java.dto;

import com.alkemy.java.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private String body;
    private Date creationDate;
    private Date lastUpdate;

    public CommentResponseDto(Comment comment) {
        this.body = comment.getBody();
        this.creationDate = comment.getCreateDate();
        this.lastUpdate = comment.getLastUpdate();
    }
}
