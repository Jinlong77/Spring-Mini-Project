package org.kshrd.gamifiedhabittracker.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.kshrd.gamifiedhabittracker.model.domain.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class JwtUtils {

    private static String SECRET = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
    private static long EXPIRATION = TimeUnit.MINUTES.toMillis(60);
//    private long EXPIRATION = 1 * (60 * 1000); // 30 min expiration


    private static String createToken(Map<String, Object> claim, String subject) {
        return Jwts.builder()
                .claims(claim)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(getSignKey()).compact();
    }

    private static SecretKey getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //2. generate token for user
    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        UserPrincipal appUser = (UserPrincipal) userDetails;
        claims.put("user_name", appUser.getUsername());
        return createToken(claims, appUser.getEmail());
    }

    //3. retrieving any information from token we will need the secret key
    private static Claims extractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //4. extract a specific claim from the JWT token’s claims.
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    //5. retrieve username from jwt token
    public static String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //6. retrieve expiration date from jwt token
    public static Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //7. check expired token
    private static Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    //8. validate token
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
