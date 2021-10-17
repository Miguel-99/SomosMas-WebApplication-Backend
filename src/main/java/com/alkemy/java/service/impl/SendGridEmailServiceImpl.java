package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDtoRequest;
import com.alkemy.java.exception.EmailNotSentException;
import com.alkemy.java.service.IEmailService;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import static com.alkemy.java.util.Constants.SPACE;

import java.io.IOException;

@Service
@PropertySource("classpath:messages/messages.properties")
public class SendGridEmailServiceImpl implements IEmailService {

    @Autowired
    private SendGrid sendGridClient;

    @Value("${email.sendgrid.from}")
    private String fromEmail;

    @Value("app.sendgrid.template.welcome")
    private String templateWelcomeId;

    @Value("sendgrid.welcome.message")
    private String sendgridWelcomeMessage;

    @Value("sendgrid.from.name")
    private String fromName;

    @Value("error.email.not.sent")
    private String emailNotSent;

    @Value("sendgrid.subject.welcome")
    private String welcome;

    @Value("${sendgrid.subject.contact}")
    private String contactSubject;

    @Value("${sendgrid.body.contact}")
    private String contactBody;

    @Autowired
    public SendGridEmailServiceImpl(SendGrid sendGrid) {
        this.sendGridClient = sendGrid;
    }

    @Override
    public void sendEmailWithTemplate(UserDtoRequest user, String subject) {

        Mail mail = addPersonalizationTemplate(subject,user.getEmail(),user.getFirstName());

        sendEmailRequest(mail);
    }

    @Override
    public void sendContactEmail(String emailTo) {
        sendEmailRequest(new Mail(new Email(fromEmail), contactSubject, new Email(emailTo), new Content("text/plain", contactBody )));
    }

    private Mail addPersonalizationTemplate(String subject, String to, String name){

        Mail mail = new Mail(new Email(fromEmail,fromName), subject, new Email(to), new Content("text/html", SPACE));

        mail.setTemplateId(templateWelcomeId);

        Personalization personalization = new Personalization();
        personalization.addTo(new Email(to));

        if(subject.equals(welcome)){
            personalization.addDynamicTemplateData("subject", subject);
            personalization.addDynamicTemplateData("name", name);
            personalization.addDynamicTemplateData("messageSubject", sendgridWelcomeMessage);
        }
        mail.addPersonalization(personalization);

        return mail;
    }

    private void sendEmailRequest(Mail mail) {

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGridClient.api(request);
        } catch (IOException ex) {
            throw new EmailNotSentException(emailNotSent);
        }

    }

}

