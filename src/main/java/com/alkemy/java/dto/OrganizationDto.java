package com.alkemy.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {

    private String name;
    private String image;
    private String phone;
    private String address;





    private List<SlidesDto> slides;

}