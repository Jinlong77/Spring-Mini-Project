package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.kshrd.gamifiedhabittracker.model.dto.request.HabitLogRequest;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_LOG_API;

@RestController
@RequestMapping(HABIT_LOG_API)
public class HabitLogController {

    //dependencies injection
    private final HabitLogService habitLogService;
    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }
    //endpoints here

    @Operation(summary = "create  new habit log")
    @PostMapping
    public ResponseEntity<?> createNewHabitLog(@RequestBody HabitLogRequest habitLogRequest) {

        return new ResponseEntity<>("create new habit-log", HttpStatus.CREATED);
    }

    @Operation(summary = "create  new habit log")
    @GetMapping("/{habit-id}")
    public ResponseEntity<?> getHabitLogById(@PathVariable long habitId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer size) {

        return new ResponseEntity<>("get by id", HttpStatus.OK);
    }
}
