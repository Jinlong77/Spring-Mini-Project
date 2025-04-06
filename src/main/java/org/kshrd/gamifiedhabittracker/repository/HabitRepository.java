package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.handler.UUIDTypeHandler;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitDto;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId} AND app_user_id = #{userUUID};
    """)
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "user", column = "app_user_id", typeHandler = UUIDTypeHandler.class,
                    one = @One(select = "findUserById")
            )
    })
    HabitEntity findHabitById(UUID habitId, UUID userUUID);

    @Select("""
        UPDATE habits
        SET title = #{habit.title}, description = #{habit.description}, frequency =  CAST(#{habit.frequency} AS frequency_type)
        WHERE habit_id = #{habitId} AND app_user_id = #{userUUID}
        RETURNING *;
    """)
    @ResultMap("habitMapper")
    HabitEntity updateHabitById(UUID habitId, @Param("habit") HabitDto habitDto, UUID userUUID);

    @Delete("""
        DELETE FROM habits
        WHERE habit_id = #{habitId} AND app_user_id = #{userUUID};
    """)
    Integer deleteHabitById(UUID habitId, UUID userUUID);

    @Select("""
        SELECT * FROM habits
        WHERE app_user_id = #{userUUID}
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size};
    """)
    @ResultMap("habitMapper")
    List<HabitEntity> findAllHabits(Integer page, Integer size, UUID userUUID);

    @Select("""
        INSERT INTO habits (title, description, frequency, app_user_id)
        VALUES (#{habit.title}, #{habit.description}, CAST(#{habit.frequency} AS frequency_type), #{id})
        RETURNING *;
    """)
    @ResultMap("habitMapper")
    HabitEntity insertNewHabit(@Param("habit") HabitDto habitDto, @Param("id") UUID userUUID);

    @Select("""
        SELECT * FROM app_users
        WHERE app_user_id = #{userUUID}
    """)
    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "app_user_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    AppUserEntity findUserById(UUID userUUID);
}
