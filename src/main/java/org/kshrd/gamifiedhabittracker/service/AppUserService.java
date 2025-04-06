package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.AppUserEntity;

import java.util.UUID;

public interface AppUserService {
    AppUserEntity getAppuserByID(UUID appId);
}
