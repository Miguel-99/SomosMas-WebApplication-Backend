package com.alkemy.java.dto;

import com.alkemy.java.model.News;
import com.alkemy.java.model.Organization;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganizationRequestDto {

    @NotEmpty(message = "{error.organization.empty.name}")
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty(message = "{error.organization.image}")
    private String image;

    @NotEmpty(message = "{error.organization.phone}")
    private String phone;

    @NotEmpty(message = "{error.organization.empty.email}")
    @Email(message = "{error.organization.wrong.email.format}")
    private String email;

    @NotEmpty(message = "{error.organization.empty.welcomeText}")
    private String welcomeText;

    @NotEmpty(message = "{error.organization.empty.aboutUsText}")
    private String aboutUsText;

    public static Organization dtoToOrg(OrganizationRequestDto dto) {
        return Organization.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .image(dto.getImage())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .welcomeText(dto.getWelcomeText())
                .aboutUsText(dto.getAboutUsText())
                .build();
    }


}
