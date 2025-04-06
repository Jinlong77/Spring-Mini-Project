package org.kshrd.gamifiedhabittracker.event.listener;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.event.UserEvent;
import org.kshrd.gamifiedhabittracker.service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserListener {

    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent userEvent) {
        switch (userEvent.getEventType()) {
            case REGISTRATION -> this.emailService.sendNewAccountEmail(userEvent.getUser().getEmail());
            case RESEND_CONFIRMATION -> this.emailService.sendResendOTPEmail(userEvent.getUser().getEmail());
        }
    }
}
