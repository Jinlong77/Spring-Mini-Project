package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.AchievementEntity;
import org.kshrd.gamifiedhabittracker.repository.AchievementRepository;
import org.kshrd.gamifiedhabittracker.service.AchievementService;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImp implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AppUserService appUserService;

    @Override
    public List<AchievementEntity> getAllAchievements(Integer page, Integer size) {
        return achievementRepository.getAllAchievementsByPagination(page,size);
    }

    @Override
    public List<AchievementEntity> getAchievementByAppUser(Integer page,Integer size) {
        var user = appUserService.getCurrentAppUserByID();
        return achievementRepository.getAchievementByUser(user.getUserId(), user.getXp(), page,size);
    }
}
