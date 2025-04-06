package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.enumeration.EventType;
import org.kshrd.gamifiedhabittracker.event.UserEvent;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.domain.UserPrincipal;
import org.kshrd.gamifiedhabittracker.model.dto.AppUser;
import org.kshrd.gamifiedhabittracker.model.dto.request.AppUserRequest;
import org.kshrd.gamifiedhabittracker.model.dto.request.RegisterRequest;
import org.kshrd.gamifiedhabittracker.repository.AppUserRepository;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.kshrd.gamifiedhabittracker.service.EmailService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.kshrd.gamifiedhabittracker.utils.AppUserUtils.getAppUserEntityFromDto;
import static org.kshrd.gamifiedhabittracker.utils.AppUserUtils.getAppUserFromAppUserEntity;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final ApplicationEventPublisher publisher;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;


    @Async
    public CompletableFuture<String> encodePassword(String password) {
        return CompletableFuture.completedFuture(encoder.encode(password));
    }

    @Override
    public AppUser getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return getAppUserByEmail(userPrincipal.getUsername());
    }

    @Override
    public AppUser updateAppUserProfile(AppUserRequest appUserRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String email = userPrincipal.getUsername();

        AppUserEntity userEntity = appUserRepository.updateAppUserProfile(email, appUserRequest);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found or could not be updated");
        }

        return getAppUserFromAppUserEntity(userEntity);
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
            publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION));
        });
        return getAppUserFromAppUserEntity(appUserRepository.findAppUserByUsername(registerRequest.getUsername()));
    }

    @Override
    public void verifyEmail(String email, String otp) {
        verificationByUserId(email, otp);
    }

    @Override
    public void resendVerificationEmail(String email) {
        resendAppUserVerificationEmail(email);
    }

    @Override
    public void deleteCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String email = userPrincipal.getUsername();

        AppUserEntity userEntity = appUserRepository.findAppUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found");
        }

        appUserRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        AppUserEntity user = Optional.ofNullable(appUserRepository.findAppUserByUsername(usernameOrEmail))
                .or(() -> Optional.ofNullable(appUserRepository.findAppUserByEmail(usernameOrEmail)))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username or email: " + usernameOrEmail));
       return new UserPrincipal(user);
    }

    private void verificationByUserId(String email, String otp) {
        AppUserEntity userEntity = appUserRepository.findAppUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found");
        }
        emailService.verifyOTP(userEntity.getEmail(), otp);
    }

    private void resendAppUserVerificationEmail(String email) {
        AppUserEntity userEntity = appUserRepository.findAppUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found");
        }
        publisher.publishEvent(new UserEvent(userEntity, EventType.RESEND_CONFIRMATION));
    }
}
