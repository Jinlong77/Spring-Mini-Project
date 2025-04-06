package org.kshrd.gamifiedhabittracker.utils;

import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.dto.AppUser;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.modelmapper.ModelMapper;


import java.util.UUID;

import static java.time.Instant.now;


public class AppUserUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static AppUser getAppUserFromAppUserEntity(AppUserEntity appUserEntity) {
        return modelMapper.map(appUserEntity, AppUser.class);
    }

    public static AppUserEntity getAppUserEntityFromDto(RegisterRequest appUser) {
        modelMapper.addConverter(appContext -> AppUserEntity.builder()
//                .userId(UUID.randomUUID())
                .username(appContext.getSource().getUsername())
                .email(appContext.getSource().getEmail())
                .level(0)
                .xp(0)
                .isVerified(false)
                .password(appContext.getSource().getPassword())
                .profileImage(appContext.getSource().getProfileImage())
                .createdAt(now())
                .build(), RegisterRequest.class, AppUserEntity.class);
        return modelMapper.map(appUser, AppUserEntity.class);
    }
}
