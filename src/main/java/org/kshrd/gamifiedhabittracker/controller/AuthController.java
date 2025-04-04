package org.kshrd.gamifiedhabittracker.controller;

import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.kshrd.gamifiedhabittracker.constant.Constant.AUTH_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(AUTH_API)
public class AuthController {

    @PostMapping("/verify")
    public ResponseEntity<Response<?>> verifyEmail() {
        return ResponseEntity.ok(getResponse("Email verified successfully", OK, null));
    }

}
