package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habits", column = "habit_id",
                    one = @One(select = "org.kshrd.gamifiedhabittracker.repository.HabitRepository.getHabitById"))
    })

    // --- Insert

    @Select("INSERT INTO habit_logs  VALUES (gen_random_uuid(),#{logDate}, CAST(#{req.status} AS habit_status), #{xpEarned}, #{req.habitId})")

    HabitLogEntity createNewHabitLogRepo(@Param("req") HabitLogRequest habitLogRequest,
                                         @Param("logDate") LocalDate logDate,
                                         @Param("xpEarned") int xpEarned);




    //get by id
    @ResultMap("habitLogMapper")
    @Select(" select  * from habit_logs where habit_id = #{habitId}")
    List<HabitLogEntity> getHabitLogByIdRepo(UUID habitId);
    //--
    @Select("SELECT COUNT(*) FROM habit_logs WHERE habit_id = CAST(#{habitId} AS UUID)")
    int getCompletionCount(String habitId);


    //---- count
    @Select("SELECT COUNT(*) FROM habit_logs WHERE habit_id = #{habitId}")
    int countLogsByHabitId(UUID habitId);
}