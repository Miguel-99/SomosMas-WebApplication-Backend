package com.alkemy.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OrganizationDto {
    private String name;
    private String image;
    private String phone;
    private String address;
    private String linkedinUrl;
    private String facebookUrl;
    private String instagramUrl;
}