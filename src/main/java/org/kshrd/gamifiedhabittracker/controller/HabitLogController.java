package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.HabitLogEntity;
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
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.CREATED;

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
            HabitLogEntity habitLog =  habitLogService.createNewHabitLogService(habitLogRequest);
            return ResponseEntity.ok(getResponse("Habit Log created successfully", CREATED, habitLog));
    }

    @Operation(summary = "get all habit log by habit ID")
    @GetMapping("/{habit-id}")
    public ResponseEntity<?> getHabitLogById(@PathVariable("habit-id") UUID habitId, @RequestParam(defaultValue = "1") @Positive Integer page, @RequestParam(defaultValue = "10") @Positive Integer size) {

        List<HabitLogEntity> habitLogs = habitLogService.getHabitLogByIdService(habitId, page, size);
        Response<List<HabitLogEntity>>  response = new Response<>(
                "Habit log retrieve successfully!",
                "OK",
                habitLogs,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
