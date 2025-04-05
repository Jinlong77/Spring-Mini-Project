package org.kshrd.gamifiedhabittracker.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppUserRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Profile image URL is required")
    private String profileImage;
}
