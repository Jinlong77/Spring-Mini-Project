package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.AppUser;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    AppUser getAppUserByEmail(String email);
    AppUser register(RegisterRequest registerRequest);
}
