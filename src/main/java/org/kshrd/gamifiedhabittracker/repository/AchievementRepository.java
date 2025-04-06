package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.AchievementEntity;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AchievementRepository {


    @Select("""
        SELECT * FROM achievements a
        INNER JOIN app_user_achievements ac ON a.achievement_id = ac.achievement_id
        WHERE ac.app_user_id = #{id} AND a.xp_required <= #{xp}
        OFFSET  #{size} * (#{page}-1)
        LIMIT   #{size};
    """)
    @Results(id = "achievementMapper",value = {
            @Result(property = "achievementId",column = "achievement_id", javaType = UUID.class),
            @Result(property = "xpRequired",column = "xp_required"),
    })
    List<AchievementEntity> getAchievementByUser(UUID id, Integer xp, Integer page, Integer size);

    @Insert("""
        INSERT INTO app_user_achievements(app_user_id, achievement_id)
        VALUES(#{appUserId}, #{achievementId})
    """)
    void insertAchievement(UUID appUserId, UUID achievementId);

    @Select("""
        SELECT * FROM achievements
    """)
    @ResultMap("achievementMapper")
    List<AchievementEntity> getAllAchievements();

    @Select("""
        SELECT * FROM achievements
        offset  #{size} * (#{page}-1)
        limit   #{size};
    """)
    @ResultMap("achievementMapper")
    List<AchievementEntity> getAllAchievementsByPagination(Integer page, Integer size);
}
