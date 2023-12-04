package br.unipar.api.ApiPillTime.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private MailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("e.cabral@edu.unipar.br");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);


    }

    }
