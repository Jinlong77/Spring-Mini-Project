package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    private String userId;
    private String username;
    private String email;
    private Integer level;
    private Integer xp;
    private String profileImage;
    private boolean isVerified;
    private Instant createdAt;
}
