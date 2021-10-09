/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.java.dto;


import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mariela
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDataMessageDto {
    
    private Date timestamp;
    private String exception;
    private List <String> messages;


}
