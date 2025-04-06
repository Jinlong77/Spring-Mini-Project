package org.kshrd.gamifiedhabittracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserEntity {

    private UUID userId;
    private String username;
    private String email;
    private String password;
    private Integer level;
    private Integer xp;
    private String profileImage;
    private boolean isVerified;
    private Instant createdAt;
}
