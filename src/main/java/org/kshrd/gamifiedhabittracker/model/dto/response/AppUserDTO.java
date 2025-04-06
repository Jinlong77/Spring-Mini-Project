package org.kshrd.gamifiedhabittracker.model.dto.response;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppUserDTO {
    private String userId;
    private String username;
    private String email;
    private Integer level;
    private Integer xp;
    private String profileImage;
    private boolean isVerified;
    private OffsetDateTime createAt;
}
