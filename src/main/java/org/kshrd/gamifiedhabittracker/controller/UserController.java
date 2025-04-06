package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.dto.HabitLogEntity;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.kshrd.gamifiedhabittracker.constant.Constant.PROFILE_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(PROFILE_API)
@RequiredArgsConstructor
public class UserController {

    private final AppUserService appUserService;

    @GetMapping
    @Operation(summary = "Get User Profile by id but static")
    public ResponseEntity<Response<?>> getUserProfile() {
        //តាមពិតប្រើ @pathVariable តែខ្ជិល
        UUID userUUID = UUID.fromString("f1a2b3c4-5d6e-7f89-a0b1-2345c678d901");
        AppUserEntity userEntity = appUserService.getAppuserByID(userUUID);
        Response<AppUserEntity>  response = new Response<>(
                "Habit log created successfully!",
                "OK",
                userEntity,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, OK);
    }

    @PutMapping
    @Operation(summary = "Update User Profile")
    public ResponseEntity<Response<?>> updateUserProfile() {

        return ResponseEntity.ok(getResponse("", OK, null));
    }

    @DeleteMapping
    @Operation(summary = "Delete User Profile")
    public ResponseEntity<Response<?>> deleteUserProfile() {

        return ResponseEntity.ok(getResponse("", OK, null));
    }
}
