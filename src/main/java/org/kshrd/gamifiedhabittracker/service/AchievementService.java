package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.dto.AchievementEntity;

import java.util.List;

public interface AchievementService {
    List<AchievementEntity> getAllAchievements();

    List<AchievementEntity> getAchievementByUserId(String userId);
}
