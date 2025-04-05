package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.kshrd.gamifiedhabittracker.model.dto.AchievementEntity;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;


import static org.kshrd.gamifiedhabittracker.constant.Constant.ACHIEVEMENT_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(ACHIEVEMENT_API)

public class AchievementController {
    @Autowired
    private AchievementService achievementService;
    @GetMapping
    @Operation(summary = "Get All Achievements")
    public ResponseEntity<Response<List<AchievementEntity>>> getAllAchievements() {
        List<AchievementEntity> achievements = achievementService.getAllAchievements();
        return ResponseEntity.ok(getResponse("Get all Achievements", OK, achievements));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get Achievement By User ID")
    public ResponseEntity<Response<List<AchievementEntity>>> getAchievementsByUserId(@PathVariable String userId) {
        List<AchievementEntity> achievements = achievementService.getAchievementByUserId(userId);

        return ResponseEntity.ok(getResponse("Get all Achievements", OK, achievements));
    }




}
