package org.kshrd.gamifiedhabittracker.model.domain;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final AppUserEntity appUserEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return appUserEntity.getPassword();
    }

    public String getEmail() {
        return appUserEntity.getEmail();
    }

    public UUID getUserId() {
        return appUserEntity.getUserId();
    }

    @Override
    public String getUsername() {
        return appUserEntity.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return appUserEntity.isVerified();
    }
}
