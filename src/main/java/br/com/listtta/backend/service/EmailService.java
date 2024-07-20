package br.com.listtta.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final String listttaMail = System.getenv("MAILADDRESS");

    @Transactional
    public void sendNewOauthUserPassword(String to, String userPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Sua senha para login no LISTTTA");
        message.setText("Essa é a sua senha de login no LISTTTA. Recomendamos alterar assim que possível: " + userPassword);
        message.setFrom(listttaMail);
        mailSender.send(message);
    }
}
