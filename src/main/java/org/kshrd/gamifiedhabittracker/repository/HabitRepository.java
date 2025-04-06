package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import java.util.List;
import java.util.UUID;


@Mapper
public interface HabitRepository {

    @Select(
            """
     select  * from  habits
     offset #{size} * (#{page}-1)
     limit #{size}
    """
    )
    @Results( id="habitMapper",value ={
            @Result(property = "habitId",column = "habit_id"),
            @Result(property = "frequencyType",column = "frequency"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "userId", column = "app_user_id")
    })
    List<HabitEntity> getAllHabit(@Param("page") Integer page ,@Param("size") Integer size);


    @Select(
            """
    select  * from habits
    where habit_id = CAST(#{id, jdbcType=VARCHAR} AS UUID)
    """
    )
    @ResultMap("habitMapper")
    HabitEntity getHabitById(@Param("id") UUID id);



}
