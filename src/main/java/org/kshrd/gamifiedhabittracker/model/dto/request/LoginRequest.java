package org.kshrd.gamifiedhabittracker.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    @Pattern(
            regexp = "^(?:[a-zA-Z0-9._-]{3,}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "Identifier should be a valid email or username"
    )
    private String identifier; // can be username or email

    @NotBlank
    private String password;
}
