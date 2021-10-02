package com.alkemy.java.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    @NotEmpty(message = "Field firstName should not be null or empty")
    @Size(min = 3, max = 20)
    private String firstName;

    @NotEmpty(message = "Field lastName should not be null or empty")
    @Size(min = 3, max = 20)
    private String lastName;

    @Email(message = "invalid email format")
    @NotBlank
    private String email;

    @Size(min = 8, max = 20)
    private String password;


}