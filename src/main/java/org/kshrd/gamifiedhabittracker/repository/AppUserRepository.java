package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "app_user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at"),
    })

    @Select("select  * from app_users where app_user_id = #{userId}")
     AppUserEntity getAppUserRepo(UUID userId);

  //---
  @Select("""
        SELECT COUNT(*) * 10 
        FROM habit_logs hl 
        JOIN habits h ON hl.habit_id = h.habit_id 
        WHERE h.app_user_id = #{userId}
        """)
  int calculateXpFromHabits(UUID userId);

    @Update("UPDATE app_users SET xp = #{newXp} WHERE app_user_id = #{userId}")
    void updateUserXp(@Param("userId") UUID userId, @Param("newXp") int newXp);

    //-
    @Update("UPDATE app_users SET level = #{level} WHERE app_user_id = #{userId}")
    void updateLevel(@Param("userId") UUID userId, @Param("level") int level);
}