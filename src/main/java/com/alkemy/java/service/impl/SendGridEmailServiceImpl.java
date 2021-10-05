package com.alkemy.java.service.impl;

import com.alkemy.java.dto.EmailRequestDto;
import com.alkemy.java.service.IEmailService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendGridEmailServiceImpl implements IEmailService {

    private SendGrid sendGridClient;

    @Value("${email.sendgrid.from}")
    private String fromEmail;

    @Value("${app.sendgrid.template}")
    private String templateId;

    @Autowired
    public SendGridEmailServiceImpl(SendGrid sendGrid) {
        this.sendGridClient = sendGrid;
    }

    @Override
    public Response sendSimpleEmail(EmailRequestDto emailRequestDto) {

        Mail mail = new Mail(new Email(fromEmail),
                emailRequestDto.getSubject() ,
                new Email(emailRequestDto.getTo()),
                new Content("text/plain",emailRequestDto.getBody()));

        return sendEmailRequest(mail);
    }

    @Override
    public Response sendEmailWithTemplate(String to) {

        Mail mail = new Mail(new Email(fromEmail), " " , new Email(to),new Content("text/html"," "));

        mail.setTemplateId(templateId);

        return sendEmailRequest(mail);
    }

    private Response sendEmailRequest(Mail mail) {
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = this.sendGridClient.api(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return response;
    }

}

