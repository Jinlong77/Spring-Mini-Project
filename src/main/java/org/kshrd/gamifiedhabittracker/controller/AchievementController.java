package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.kshrd.gamifiedhabittracker.model.AchievementEntity;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<Response<List<AchievementEntity>>> getAllAchievements(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer size) {
        List<AchievementEntity> achievements = achievementService.getAllAchievements(page,size);
        return ResponseEntity.ok(getResponse("Get all Achievements", OK, achievements));
    }


    @GetMapping("/app_users")
    @Operation(summary = "Get Achievement By App User ID")
    public ResponseEntity<Response<List<AchievementEntity>>> getAchievementsByUserId(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer size) {
        List<AchievementEntity> achievements = achievementService.getAchievementByAppUser(page,size);

        return ResponseEntity.ok(getResponse("Get all Achievements", OK, achievements));
    }

}
