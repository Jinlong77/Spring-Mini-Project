package org.kshrd.gamifiedhabittracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.request.LoginRequest;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AuthResponse;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.kshrd.gamifiedhabittracker.service.EmailService;
import org.kshrd.gamifiedhabittracker.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.kshrd.gamifiedhabittracker.constant.Constant.AUTH_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final JwtService jwtService;

    @PostMapping("/verify")
    public ResponseEntity<Response<?>> verifyEmail(@RequestParam String email, @RequestParam String otp) {
        appUserService.verifyEmail(email, otp);
        return ResponseEntity.ok(getResponse("User registered successfully! Please verify your email to complete the registration.", OK, null));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<?>> authenticate(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        authentication(loginRequest.getIdentifier(), loginRequest.getPassword());
        UserDetails userDetails = appUserService.loadUserByUsername(loginRequest.getIdentifier());
        String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(getResponse("Login successful", OK, authResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<Response<?>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.status(CREATED).body(getResponse("User registered successfully", OK, appUserService.register(registerRequest)));
    }

    @PostMapping("/resend")
    @Operation(summary = "Resend OTP to email")
    public ResponseEntity<?> resendOtp(@RequestParam String email) {
        appUserService.resendVerificationEmail(email);
        return ResponseEntity.ok(getResponse("Resend OTP successfully! Please check your email.", OK, null));
    }

    private void authentication(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
