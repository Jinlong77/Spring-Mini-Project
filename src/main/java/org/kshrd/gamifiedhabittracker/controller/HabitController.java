package org.kshrd.gamifiedhabittracker.controller;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_API;


@RequiredArgsConstructor
@RestController
@RequestMapping(HABIT_API)
public class HabitController {

    private final HabitService habitService;


    @GetMapping
    Response<List<HabitEntity>> getAllHabit(@RequestParam(defaultValue = "1") Integer size, @RequestParam(defaultValue = "10") Integer page){

        List<HabitEntity> finAll = habitService.getAllHabit(size,page);

        return new Response<>(
                "Find habit successfully ",
                "OK",
                finAll,
                LocalDateTime.now()
        );
    }

    @GetMapping("/{habit-id}")
    Response<HabitEntity> getHabitById(@PathVariable("habit-id") UUID id) {

        HabitEntity findId = habitService.getHabitById(id);
        return new Response<>(
                "Find habit by Id successfully ",
                "OK",
                findId,
                LocalDateTime.now()
        );
    }
}
