package org.kshrd.gamifiedhabittracker.service.implementation;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.AchievementEntity;
import org.kshrd.gamifiedhabittracker.repository.AchievementRepository;
import org.kshrd.gamifiedhabittracker.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImp implements AchievementService {
    @Autowired
    private AchievementRepository achievementRepository;
    @Override
    public List<AchievementEntity> getAllAchievements() {
        return achievementRepository.getAllAchievements();
    }

    @Override
    public List<AchievementEntity> getAchievementByUserId(String userId) {
        return achievementRepository.findAchievementByUserId(userId);
    }
}
