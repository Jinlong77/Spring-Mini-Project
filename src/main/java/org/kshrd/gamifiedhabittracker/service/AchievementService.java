package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.AchievementEntity;

import java.util.List;

public interface AchievementService {
    List<AchievementEntity> getAllAchievements(Integer page, Integer size);

    List<AchievementEntity> getAchievementByAppUser(Integer page,Integer size);
}
