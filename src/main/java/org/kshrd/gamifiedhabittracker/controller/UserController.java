package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.kshrd.gamifiedhabittracker.constant.Constant.PROFILE_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(PROFILE_API)
public class UserController {


//    @GetMapping
//    @Operation(summary = "Get User Profile")
//    public ResponseEntity<Response<?>> getUserProfile() {
//
//        return ResponseEntity.ok(getResponse("", OK, null));
//    }
//
//    @PutMapping
//    @Operation(summary = "Update User Profile")
//    public ResponseEntity<Response<?>> updateUserProfile() {
//
//        return ResponseEntity.ok(getResponse("", OK, null));
//    }
//
//    @DeleteMapping
//    @Operation(summary = "Delete User Profile")
//    public ResponseEntity<Response<?>> deleteUserProfile() {
//
//        return ResponseEntity.ok(getResponse("", OK, null));
//    }
}
