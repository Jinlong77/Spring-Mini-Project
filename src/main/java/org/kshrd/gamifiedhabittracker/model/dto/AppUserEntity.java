package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserEntity {

    private String userId;
    private String username;
    private String email;
    private String password;
    private Integer level;
    private Integer xp;
    private String profileImage;
    private boolean isVerified;
    private Instant createAt;
}
