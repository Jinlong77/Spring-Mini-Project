package org.kshrd.gamifiedhabittracker.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserResponse {

    @Schema(description = "The unique identifier of the user", required = true)
    private String appUserId;

    @Schema(description = "The username of the user", required = true)
    private String username;

    @Schema(description = "The email address of the user", required = true)
    private String email;

    @Schema(description = "The level of the user in the gamified system", required = true)
    private Integer level;

    @Schema(description = "The experience points (XP) of the user", required = true)
    private Integer xp;

    @Schema(description = "The URL of the user's profile image")
    private String profileImageUrl;

    @Schema(description = "Indicates whether the user's account is verified", required = true)
    private boolean isVerified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @Schema(description = "The timestamp when the user account was created", required = true)
    private LocalDateTime createdAt;
}