package com.alkemy.java.dto;

import javax.validation.constraints.NotEmpty;

public class CommentRequestDto {

    @NotEmpty(message = "{error.body.empty}")
    private String body;

}
