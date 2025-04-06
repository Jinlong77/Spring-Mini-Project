package org.kshrd.gamifiedhabittracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.request.LoginRequest;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AuthResponse;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.kshrd.gamifiedhabittracker.constant.Constant.AUTH_API;
import static org.kshrd.gamifiedhabittracker.utils.JwtUtils.generateToken;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/verify")
    public ResponseEntity<Response<?>> verifyEmail() {
        return ResponseEntity.ok(getResponse("Email verified successfully", OK, null));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<?>> authenticate(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        authentication(loginRequest.getIdentifier(), loginRequest.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(loginRequest.getIdentifier());
        final String token = generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(getResponse("Login successful", OK, authResponse));
    }

    @PostMapping("/register")
    @PostAuthorize("permitAll()")
    public ResponseEntity<Response<?>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(getResponse("User registered successfully", OK, appUserService.register(registerRequest)));
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
