package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.kshrd.gamifiedhabittracker.model.HabitEntity;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    List<HabitEntity> findAllByUserId(String habitId);
}
