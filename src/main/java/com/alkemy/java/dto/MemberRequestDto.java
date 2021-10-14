package com.alkemy.java.dto;

import com.alkemy.java.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {


    @NotEmpty(message = "{error.member.name}")
    private String name;
    @NotEmpty(message = "{error.member.facebook}")
    private String facebookUrl;
    @NotEmpty(message = "{error.member.instagram}")
    private String instagramUrl;
    @NotEmpty(message = "{error.member.linkedin}")
    private String linkedinUrl;
    @NotEmpty(message = "{error.member.image}")
    private String image;
    @NotEmpty(message = "{error.member.description}")
    private String description;

    public static Member dtoToMember(MemberRequestDto dto) {
        return Member.builder()
                .name(dto.getName())
                .facebookUrl(dto.getFacebookUrl())
                .instagramUrl(dto.getInstagramUrl())
                .linkedinUrl(dto.getLinkedinUrl())
                .deleted(false)
                .image(dto.getImage())
                .description(dto.getDescription())
                .build();
    }
}
