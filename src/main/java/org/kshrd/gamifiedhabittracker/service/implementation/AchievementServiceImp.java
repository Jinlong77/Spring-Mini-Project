package org.kshrd.gamifiedhabittracker.service.implementation;

import org.kshrd.gamifiedhabittracker.model.AchievementEntity;
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
    public List<AchievementEntity> getAllAchievements(Integer page, Integer size) {
        return achievementRepository.getAllAchievements(page,size);
    }

    @Override
    public List<AchievementEntity> getAchievementByAppUser(Integer page,Integer size) {
        return achievementRepository.getAchievementByUser(page,size);
    }
}
