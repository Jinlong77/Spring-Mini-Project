package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.AchievementEntity;

import java.util.List;

@Mapper
public interface AchievementRepository {


    @Select("SELECT * FROM achievements")
    @Results(id = "achievements",value = {
            @Result(property = "achievementId",column = "achievement_id"),
            @Result(property = "xpRequired",column = "xp_required")
    })
    List<AchievementEntity> getAllAchievements();

    @Select("""
    SELECT * FROM app_user_achievements aua
    JOIN achievements a ON aua.achievement_id = a.achievement_id
    WHERE aua.app_user_id = #{app_user_id}
    """)
    @Result(property = "userId",column = "app_user_id")
    @ResultMap("achievements")
    List<AchievementEntity> findAchievementByUserId(String userId);
}
