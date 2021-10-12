package com.alkemy.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequestDto {

    @NotEmpty(message = "error.name.empty")
    @Size(min = 5, max = 50, message = "error.name.size")
    private String name;

    @NotEmpty(message = "error.content.empty")
    private String content;

    @NotEmpty(message = "error.image.empty")
    private String image;

}
