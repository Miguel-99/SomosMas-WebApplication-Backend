package com.alkemy.java.dto;

import lombok.Data;

@Data
public class MemberResponseDto {

    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
}
