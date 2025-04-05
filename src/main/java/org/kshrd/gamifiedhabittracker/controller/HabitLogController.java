package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_LOG_API;

@RestController
@RequestMapping(HABIT_LOG_API)
@RequiredArgsConstructor
public class HabitLogController {

    //dependencies injection
    private final HabitLogService habitLogService;

    //endpoints here

    @Operation(summary = "create  new habit log")
    @PostMapping
    public ResponseEntity<?> createNewHabitLog(@RequestBody HabitLogRequest habitLogRequest) {
        try {
            habitLogService.createNewHabitLogService(habitLogRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Habit Log created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Habit Log");
        }
    }


    @Operation(summary = "get all habit log by habit ID")
    @GetMapping("/{habit-id}")
    public ResponseEntity<?> getHabitLogById(@PathVariable("habit-id") UUID habitId) {

        List<HabitLogEntity> getteHabitLogById = habitLogService.getHabitLogByIdService(habitId);
        Response<List<HabitLogEntity>>  response = new Response<>(
                "Habit log retrive successfully!",
                "OK",
                getteHabitLogById,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
