package com.alkemy.java.service;

import com.alkemy.java.dto.EmailRequestDto;
import com.sendgrid.Response;

public interface IEmailService {
    public Response sendSimpleEmail(EmailRequestDto emailRequestDto);
    public Response sendEmailWithTemplate(String to);
}
