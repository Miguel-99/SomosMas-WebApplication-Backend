
package com.alkemy.java.dto;

import com.alkemy.java.model.Organization;
import lombok.Data;

import java.util.Date;

@Data
public class SlideResponseDto {

    private String imageUrl;
    private int orderNumber;
    private Long id;
    private String text;
    private Organization organizationId;
    private Date createDate;
    private Date lastUpdate;



}
