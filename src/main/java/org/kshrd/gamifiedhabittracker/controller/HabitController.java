package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitDto;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(HABIT_API)
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/{habit-id}")
    @Operation( summary = "Get habit by ID", description = "Fetches a specific habit by its ID." )
    public ResponseEntity<Response<HabitEntity>> getHabitById(@PathVariable("habit-id") UUID habitId){
        HabitEntity habit = habitService.getHabitById(habitId);
        return ResponseEntity.ok(getResponse("Habit fetched successfully!", OK, habit));
    }

    @PutMapping("/{habit-id}")
    @Operation( summary = "Update habit by ID", description = "Updates the details of an existing habit by its ID." )
    public ResponseEntity<Response<HabitEntity>> updateHabitById(@PathVariable("habit-id") UUID habitId, @RequestBody @Valid HabitDto habitDto){
        HabitEntity habit = habitService.updateHabitById(habitId, habitDto);
        return ResponseEntity.ok(getResponse("Habit updated successfully!", OK, habit));
    }

    @DeleteMapping("/{habit-id}")
    @Operation( summary = "Delete habit by ID", description = "Deletes a habit by its ID." )
    public ResponseEntity<Response<HabitEntity>> removeHabitById(@PathVariable("habit-id") UUID habitId){
        habitService.removeHabitById(habitId);
        return ResponseEntity.ok(getResponse("Habit deleted successfully!", OK, null));
    }

    @GetMapping
    @Operation( summary = "Get all habits", description = "Fetches a paginated list of all habits." )
    public ResponseEntity<Response<List<HabitEntity>>> getAllHabits(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer limit){
        List<HabitEntity> habit = habitService.getAllHabits(offset, limit);
        return ResponseEntity.ok(getResponse("Fetched all habits successfully!", OK, habit));
    }

    @PostMapping
    @Operation( summary = "Create a new habit", description = "Creates a new habit with the provided details." )
    public ResponseEntity<Response<HabitEntity>> addNewHabit(@RequestBody @Valid HabitDto habitDto){
        HabitEntity habit = habitService.addNewHabit(habitDto);
        return ResponseEntity.ok(getResponse("Habit created successfully!", OK, habit));
    }
}
