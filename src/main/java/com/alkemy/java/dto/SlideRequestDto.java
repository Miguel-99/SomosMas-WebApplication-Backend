
package com.alkemy.java.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlideRequestDto {
    
    
    @NotBlank(message = "Field Text should not be null or empty")
    private String text;

    private Integer numberOrder;

    @NotNull (message = "Field organizatioDto should not be null or empty")
    private OrganizationRequestForSlideDto organizationDto;

   
}
