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

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_LOG_API;

@RestController
@RequestMapping(HABIT_LOG_API)
@RequiredArgsConstructor
public class HabitLogController {

    //dependencies injection
    private final HabitLogService habitLogService;
//    public HabitLogController(HabitLogService habitLogService) {
//        this.habitLogService = habitLogService;
//    }
    //endpoints here

    @Operation(summary = "create  new habit log")
    @PostMapping
    public ResponseEntity<Response<HabitLogEntity>> createNewHabitLog(@RequestBody HabitLogRequest habitLogRequest) {
        HabitLogEntity createHabitLog = habitLogService.createNewHabitLogService(habitLogRequest);
        Response<HabitLogEntity>  response = new Response<>(
                "Habit log created successfully!",
                "OK",
                createHabitLog,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "get all habit log by habit ID")
    @GetMapping("/{habit-id}")
    public ResponseEntity<?> getHabitLogById(@PathVariable("habit-id") long habitId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer size) {


        return new ResponseEntity<>("get by id", HttpStatus.OK);
    }


}
