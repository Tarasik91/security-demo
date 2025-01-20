package com.example.springsecuritydemo.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class AppConfig {

    private static final String MAIL_HOST_PROP = "mail.host";
    private static final String MAIL_PORT_PROP = "mail.port";
    private static final String MAIL_USERNAME_PROP = "mail.userName";
    private static final String MAIL_PASSWORD_PROP = "mail.password";
    private static final String MAIL_PROTOCOL_PROP = "mail.transport.protocol";
    private static final String MAIL_AUTH_PROP = "mail.smtp.auth";
    private static final String MAIL_STARTTLS_PROP = "mail.smtp.starttls.enable";

    @Resource
    private Environment env;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getRequiredProperty(MAIL_HOST_PROP));
        mailSender.setPort(env.getRequiredProperty(MAIL_PORT_PROP, Integer.class));

        mailSender.setUsername(env.getRequiredProperty(MAIL_USERNAME_PROP));
        mailSender.setPassword(env.getRequiredProperty(MAIL_PASSWORD_PROP));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", env.getRequiredProperty(MAIL_PROTOCOL_PROP));
        props.put("mail.smtp.auth", env.getRequiredProperty(MAIL_AUTH_PROP));
        props.put("mail.smtp.starttls.enable", env.getRequiredProperty(MAIL_STARTTLS_PROP));
      //  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.debug", true);
        return mailSender;
    }
}
