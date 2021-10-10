package com.alkemy.java.dto;

import com.alkemy.java.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
public class NewsDto {

    private Long id;

    @Size(min = 2, max = 255)
    private String name;

    @Size(min = 2, max = 255)
    private String content;

    @Size(min = 2, max = 255)
    private String image;

    private Category category;
}
