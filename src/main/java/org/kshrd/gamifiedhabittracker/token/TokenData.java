package org.kshrd.gamifiedhabittracker.token;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kshrd.gamifiedhabittracker.model.dto.AppUser;

@Getter
@Setter
@Builder
public class TokenData {

    private AppUser appUser;
    private Claims claims;
    private boolean valid;
}
