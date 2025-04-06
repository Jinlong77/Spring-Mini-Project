package org.kshrd.gamifiedhabittracker.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserVerificationEntity {

    private UUID verificationId;
    private UUID userId;
    private String otp;
    private LocalDateTime expirationTime;
    private LocalDateTime createdAt;
}
