package org.kshrd.gamifiedhabittracker.service.implementation;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.repository.AppUserRepository;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserServiceImp implements AppUserService {
    private final AppUserRepository appUserRepository;


    @Override
    public AppUserEntity getAppuserByID(UUID userId) {
        return appUserRepository.getAppUserRepo(userId);
    }
}
