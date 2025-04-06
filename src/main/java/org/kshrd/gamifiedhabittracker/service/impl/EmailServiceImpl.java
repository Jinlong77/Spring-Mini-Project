package org.kshrd.gamifiedhabittracker.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.AppUserVerificationEntity;
import org.kshrd.gamifiedhabittracker.repository.AppUserRepository;
import org.kshrd.gamifiedhabittracker.repository.AppUserVerificationRepository;
import org.kshrd.gamifiedhabittracker.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.util.Optional;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.kshrd.gamifiedhabittracker.utils.OTPUtils.generateOTP;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final AppUserVerificationRepository appUserVerificationRepository;
    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;
    private final AppUserRepository appUserRepository;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendNewAccountEmail(String email) {
        try {
            String otp = generateOTP(6);
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject("Account Verification");
            helper.setFrom(fromEmail);
            helper.setTo(email);

            Context context = new Context();
            context.setVariable("otp", otp);
            context.setVariable("host", host);

            String htmlContent = templateEngine.process("email/verification", context);
            helper.setText(htmlContent, true);

            saveOTP(email, otp);
            sender.send(message);

        } catch (Exception e) {
            throw new ApiException("Failed to send email: " + e.getMessage());
        }
    }

    @Override
    public void sendResendOTPEmail(String email) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String otp = generateOTP(6);

            helper.setSubject("OTP Resent");
            helper.setFrom(fromEmail);
            helper.setTo(email);

            Context context = new Context();
            context.setVariable("otp", otp);
            context.setVariable("host", host);

            String htmlContent = templateEngine.process("email/resend-otp", context);
            helper.setText(htmlContent, true);

            saveOTP(email, otp);
            sender.send(message);

        } catch (Exception e) {
            throw new ApiException("Failed to send email: " + e.getMessage());
        }
    }

    @Override
    public void verifyOTP(String email, String otp) {
        Optional<AppUserEntity> userOptional = Optional.ofNullable(appUserRepository.findAppUserByEmail(email));
        if (userOptional.isEmpty()) throw new ApiException("User not found");

        AppUserEntity user = userOptional.get();
        Optional<AppUserVerificationEntity> verificationOptional = Optional.ofNullable(appUserVerificationRepository.findByAppUserId(user.getUserId()));

        if (verificationOptional.isEmpty()) throw new ApiException("OTP not found");

        AppUserVerificationEntity verification = verificationOptional.get();
        if (!verification.getOtp().equals(otp)) throw new ApiException("Invalid OTP");

        if (verification.getExpirationTime().isBefore(now())) throw new ApiException("OTP expired, please resend OTP");

        user.setVerified(true);
        appUserRepository.updateAppUser(user);

        appUserVerificationRepository.deleteByAppUserId(user.getUserId());
    }

    private void saveOTP(String email, String otp) {
        Optional<AppUserEntity> userOptional = Optional.ofNullable(appUserRepository.findAppUserByEmail(email));
        if (userOptional.isEmpty()) throw new ApiException("User not found");

        AppUserEntity user = userOptional.get();

        Optional<AppUserVerificationEntity> verificationOptional = Optional.ofNullable(appUserVerificationRepository.findByAppUserId(user.getUserId()));

        AppUserVerificationEntity verification;
        if (verificationOptional.isPresent()) {
            if (verificationOptional.get().getExpirationTime().isAfter(now())) {
                throw new ApiException("OTP already sent, please wait for the expiration time");
            }
            appUserVerificationRepository.deleteByAppUserId(user.getUserId());
            verification = verificationOptional.get();
        } else {
            verification = new AppUserVerificationEntity();
            verification.setVerificationId(UUID.randomUUID());
            verification.setUserId(user.getUserId());
            verification.setCreatedAt(now());
        }

        verification.setOtp(otp);
        verification.setExpirationTime(now().plusSeconds(120));

        appUserVerificationRepository.save(verification);
        var isOtp = appUserVerificationRepository.findByOtp(otp);
        if (isOtp == null) {
            throw new ApiException("Failed to save OTP");
        }
    }
}
