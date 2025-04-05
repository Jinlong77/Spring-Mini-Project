package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.AchievementEntity;

import java.util.List;

@Mapper
public interface AchievementRepository {

    @Select("""
    SELECT * FROM achievements
    offset  #{size} * (#{page}-1)
    limit   #{size}
    """)
    @Results(id = "achievements",value = {
            @Result(property = "achievementId",column = "achievement_id"),
            @Result(property = "xpRequired",column = "xp_required")
    })
    List<AchievementEntity> getAllAchievements(Integer page, Integer size);

    @Select("""
    SELECT * FROM achievements a
    JOIN habit_logs hl ON a.xp_required = hl.xp_earned
    WHERE hl.status = 'COMPLETED' AND hl.xp_earned >= 50  
    offset  #{size} * (#{page}-1)
    limit   #{size}
    """)
    @Results(id = "achievementWithLogs", value = {
            @Result(property = "achievementId", column = "achievement_id"),
            @Result(property = "xpRequired", column = "xp_required"),
            @Result(property = "xpEarned", column = "xp_earned")
    })
    List<AchievementEntity> getAchievementByUser(Integer page, Integer size);
}
