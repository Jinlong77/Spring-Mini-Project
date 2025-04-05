package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.AppUserRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserDTO;

public interface AppUserService {
    AppUserDTO getAppUser();
    AppUserDTO updateAppUser(AppUserRequest appUserRequest);
    void deleteAppUser(String email);
}
