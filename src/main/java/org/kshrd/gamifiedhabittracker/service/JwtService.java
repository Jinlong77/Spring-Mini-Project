package org.kshrd.gamifiedhabittracker.service;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {

    String generateToken(UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractEmail(String token);

    Date extractExpirationDate(String token);

    Boolean validateToken(String token, UserDetails userDetails);
}
