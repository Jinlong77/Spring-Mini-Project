package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.domain.UserPrincipal;
import org.kshrd.gamifiedhabittracker.model.dto.AppUser;
import org.kshrd.gamifiedhabittracker.model.dto.request.LoginRequest;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AuthResponse;
import org.kshrd.gamifiedhabittracker.repository.AppUserRepository;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.kshrd.gamifiedhabittracker.utils.AppUserUtils.getAppUserEntityFromDto;
import static org.kshrd.gamifiedhabittracker.utils.AppUserUtils.getAppUserFromAppUserEntity;
import static org.kshrd.gamifiedhabittracker.utils.JwtUtils.generateToken;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder encoder;


    @Async
    public CompletableFuture<String> encodePassword(String password) {
        return CompletableFuture.completedFuture(encoder.encode(password));
    }

    @Override
    public AppUser getAppUserByEmail(String email) {
        AppUserEntity userEntity = appUserRepository.findAppUserByEmail(email);
        if (userEntity == null) throw new ResourceNotFoundException("User not found");
        return getAppUserFromAppUserEntity(userEntity);
    }

    @Override
    public AppUser register(RegisterRequest registerRequest) {
        AppUserEntity existingUsername = appUserRepository.findAppUserByUsername(registerRequest.getUsername());
        AppUserEntity existingEmail = appUserRepository.findAppUserByEmail(registerRequest.getEmail());
        if (existingUsername != null) throw new ApiException("Username already exists");
        if (existingEmail != null) throw new ApiException("Email already exists");
        encodePassword(registerRequest.getPassword()).thenAccept(encodedPassword -> {
            registerRequest.setPassword(encodedPassword);
            AppUserEntity userEntity = appUserRepository.createAppUser(getAppUserEntityFromDto(registerRequest));
            if (userEntity == null) throw new ApiException("User cannot be created");
        });
        var appUser = appUserRepository.findAppUserByUsername(registerRequest.getUsername());
        if (appUser == null) {
            throw new ApiException("User cannot be found after creation");
        }
        return getAppUserFromAppUserEntity(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        AppUserEntity user = Optional.ofNullable(appUserRepository.findAppUserByUsername(usernameOrEmail))
                .or(() -> Optional.ofNullable(appUserRepository.findAppUserByEmail(usernameOrEmail)))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username or email: " + usernameOrEmail));
       return new UserPrincipal(user);
    }
}
