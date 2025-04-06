package org.kshrd.gamifiedhabittracker.security.provider;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ApiException;
import org.kshrd.gamifiedhabittracker.model.domain.UserPrincipal;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameOrEmail = authentication.getName();
        String password = (String) authentication.getCredentials();

//        UsernamePasswordAuthenticationToken authToken = apiAuthenticationTokenFunction.apply(authentication);
        UserPrincipal userDetails = (UserPrincipal) appUserService.loadUserByUsername(usernameOrEmail);

        if (userDetails != null) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                validAccount.accept(userDetails);
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            } else {
                throw new ApiException("Invalid credentials");
            }
        } else {
            throw new ApiException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private final Function<Authentication, UsernamePasswordAuthenticationToken> apiAuthenticationTokenFunction = authentication -> (UsernamePasswordAuthenticationToken) authentication;


    private final Consumer<UserPrincipal> validAccount = userPrincipal -> {
        if (!userPrincipal.isEnabled()) throw new DisabledException("Your email is not verified");
    };
}
