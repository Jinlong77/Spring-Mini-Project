package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequencyType", column = "frequency"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUser", column = "app_user_id",
                    one = @One(select = "org.kshrd.gamifiedhabittracker.repository.AppUserRepository.getAppUserRepo"))
    })
    @Select("SELECT * FROM habits WHERE app_user_id = #{userId}")
    List<HabitEntity> getHabitsByUserIdRepo(UUID userId);


    @ResultMap("habitMapper")
    @Select("SELECT * FROM habits WHERE habit_id = #{habitId}")
    HabitEntity findHabitById(@Param("habitId") UUID habitId);

    //--
    @ResultMap("habitMapper")
    @Select("SELECT * FROM habits WHERE habit_id = #{habitId}")
    HabitEntity getHabitById(UUID habitId);


}
