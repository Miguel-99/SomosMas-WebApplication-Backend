/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.java.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mariela
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    
    private String name;

    private String description;

    private String image;

    private Date createDate;
}
