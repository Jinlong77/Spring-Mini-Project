package org.kshrd.gamifiedhabittracker.service;

public interface EmailService {
    void sendNewAccountEmail();

    void sendResetPasswordEmail();
}
