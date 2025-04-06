package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.AchievementEntity;

import java.util.List;

@Mapper
public interface AchievementRepository {

    @Select("""
        SELECT * FROM achievements
        offset  #{size} * (#{page}-1)
        limit   #{size};
    """)
    @Results(id = "achievementMapper",value = {
            @Result(property = "achievementId",column = "achievement_id"),
            @Result(property = "xpRequired",column = "xp_required")
    })
    List<AchievementEntity> getAllAchievements(Integer page, Integer size);

    @Select("""
        SELECT * FROM app_users a_user
        INNER JOIN app_user_achievements a_user_achieve ON a_user.app_user_id = a_user_achieve.app_user_id
        WHERE a_user_achieve.achievement_id = #{id} 
        OFFSET  #{size} * (#{page}-1)
        LIMIT   #{size};
    """)
    @ResultMap("achievementMapper")
    List<AchievementEntity> getAchievementByUser(Integer page, Integer size);
}
