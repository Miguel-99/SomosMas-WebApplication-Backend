
package com.alkemy.java.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlideRequestDto {
    
    
    
    private String text;

    private Integer numberOrder;

    private OrganizationRequestForSlideDto organizationDto;

   
}
