package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.request.AppUserRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserDTO;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.kshrd.gamifiedhabittracker.constant.Constant.PROFILE_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROFILE_API)
public class UserController {
    private final AppUserService appUserService;

    @GetMapping
    @Operation(summary = "Get User Profile")
    public ResponseEntity<Response<AppUserDTO>> getUserProfile() {
        AppUserDTO  appUserDTO =  appUserService.getAppUser();
        return ResponseEntity.ok(getResponse("User profile fetched successfully!", OK, appUserDTO));
    }

    @PutMapping
    @Operation(summary = "Update User Profile")
        public ResponseEntity<Response<AppUserDTO>> updateUserProfile(
            @RequestBody @Valid  AppUserRequest request
        ) {
            AppUserDTO appUserDTO = appUserService.updateAppUser(request);
            return ResponseEntity.ok(getResponse("User profile updated successfully!", OK, appUserDTO));
        }

    @DeleteMapping
    @Operation(summary = "Delete User Profile")
    public ResponseEntity<Response<?>> deleteUserProfile() {
        appUserService.deleteAppUser("mike@example.com");
        return ResponseEntity.ok(getResponse("User profile deleted successfully!", OK, null));
    }
}
