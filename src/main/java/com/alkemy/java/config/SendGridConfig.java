package com.alkemy.java.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sendgrid.properties")
public class SendGridConfig {

    @Value("${app.sendgrid.key}")
    String sendGridAPIKey;

    @Bean
    public SendGrid sendGrid() {
       return new SendGrid(sendGridAPIKey);
    }

}