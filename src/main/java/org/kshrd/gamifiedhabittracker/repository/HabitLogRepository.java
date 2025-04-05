package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

//    private UUID habitLogId;
//    private LocalDateTime logDate;
//    private String status;
//    private Integer xpEarned;
//    private HabitEntity habits;

    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habits", column = "habit_id",
                    one = @One(select = "org.kshrd.gamifiedhabittracker.repository.HabitRepository.getHabitById"))
    })
    //org.kshrd.gamifiedhabittracker.repository.HabitRepository.getHabitById
    // --- Insert
    @Select("INSERT INTO habit_logs (log_date, status, xp_earned, habit_id) VALUES (#{logDate}, #{req.status}, #{xpEarned}, #{req.habitId})")
    HabitLogEntity createNewHabitLogRepo(@Param("req") HabitLogRequest habitLogRequest);


    //get by id
    @ResultMap("habitLogMapper")
    @Select(" select  * from habit_logs where habit_id = #{habitId}")
    List<HabitLogEntity> getHabitLogByIdRepo(UUID habitId);
    //--
    @Select("SELECT COUNT(*) FROM habit_logs WHERE habit_id = CAST(#{habitId} AS UUID)")
    int getCompletionCount(String habitId);
}