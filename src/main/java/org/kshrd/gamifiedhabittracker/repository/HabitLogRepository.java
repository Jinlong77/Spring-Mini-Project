package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserResponse;
@Mapper
public interface HabitLogRepository {
    @Insert("""
    INSERT INTO habit_logs (habit_log_id, log_date, status, xp_earned, habit_id)
    VALUES (gen_random_uuid(), #{logDate}, CAST(#{status} AS habit_status), #{xpEarned}, #{habitId})
    RETURNING habit_log_id, log_date, status, xp_earned, habit_id;
""")
    @Results({
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habit.habitId", column = "habit_id") // Optional mapping
    })
    HabitLogEntity createNewHabitLogRepo(HabitLogRequest request);


    @Select("SELECT COUNT(*) FROM habit_logs WHERE habit_id = CAST(#{habitId} AS UUID)")
    int getCompletionCount(String habitId);

}