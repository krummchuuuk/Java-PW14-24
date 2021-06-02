package com.example.PW14.java.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@PropertySource(value = "classpath:mail.properties")
public class MailService {
    private String username;
    private String password;
    private Properties properties;
    Environment env;

    @Autowired
    private void setEnv(Environment env) {
        this.env = env;
    }
    public Properties mailProperties() {
        this.username = env.getProperty("mail.smtp.username");
        this.password = env.getProperty("mail.smtp.password");
        properties = new Properties();
        properties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        properties.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
        return properties;
    }
    @Async
    public void sendMessage(String subject, String text) {
        mailProperties();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println(username);
                System.out.println(password);
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(env.getProperty("mail.smtp.reciever")));

            message.setSubject(subject);

            message.setText(text);

            Transport.send(message);
        } 
        catch (MessagingException exception) {
            throw new RuntimeException(exception);
        }
    }
}
