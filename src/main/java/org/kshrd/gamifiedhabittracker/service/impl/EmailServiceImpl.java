package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendNewAccountEmail() {
        try{
             SimpleMailMessage message = new SimpleMailMessage();
//             message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
//             message.setFrom(this.fromEmail);
//             message.setTo(email);
//             message.setText(EmailUtil.getNewAccountEmail(name, this.host, token));
             this.sender.send(message);
        } catch (Exception e) {
             throw new ApiException("Failed to send email");
        }
    }

    @Override
    public void sendResetPasswordEmail() {

    }
}
