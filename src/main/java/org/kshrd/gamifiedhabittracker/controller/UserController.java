package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.kshrd.gamifiedhabittracker.constant.Constant.PROFILE_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(PROFILE_API)
@RequiredArgsConstructor
public class UserController {

    private final AppUserService appUserService;

    @GetMapping("/{email}")
    @Operation(summary = "Get User Profile")
    public ResponseEntity<Response<?>> getUserProfile(@PathVariable String email) {
        return ResponseEntity.ok(getResponse("User profile fetched successfully!", OK, appUserService.getAppUserByEmail(email)));
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

    @PostMapping
    @Operation(summary = "Create User Profile")
    public ResponseEntity<Response<?>> createUserProfile(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.status(CREATED).body(getResponse("", OK, appUserService.register(registerRequest)));
    }
}