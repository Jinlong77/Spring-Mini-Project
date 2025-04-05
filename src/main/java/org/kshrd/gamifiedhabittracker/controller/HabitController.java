package org.kshrd.gamifiedhabittracker.controller;

import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.implementation.HabitServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.kshrd.gamifiedhabittracker.constant.Constant.HABIT_API;

@RestController
@RequestMapping(HABIT_API)
public class HabitController {

    private final HabitServiceImp habitService;

    public HabitController(HabitServiceImp habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public ResponseEntity<?> getAllHabits() {
        UUID userUUID = UUID.fromString("f1a2b3c4-5d6e-7f89-a0b1-2345c678d901");
        List<HabitEntity> habitEntity = habitService.getHabitsByUserId( userUUID);

        Response<List<HabitEntity>> response = new Response<>(
                "Habit log created successfully!",
                "OK",
                habitEntity,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
