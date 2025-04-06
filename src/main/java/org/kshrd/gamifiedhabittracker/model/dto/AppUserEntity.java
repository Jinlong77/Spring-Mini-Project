package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
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
    private LocalDateTime createAt;
}
