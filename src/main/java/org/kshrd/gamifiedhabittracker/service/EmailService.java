package org.kshrd.gamifiedhabittracker.service;

public interface EmailService {

    void sendNewAccountEmail(String email);
    void sendResendOTPEmail(String email);
    void verifyOTP(String email, String otp);
}
