/*package com.desafioPerc.ms_main.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * Represents class who handle the token logic
 */
/*@Component
public class JwtProvider {

    private final String USER_ID = "1";
    private final String USER_NAME = "MasterAcc";

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiriaton;

    /**
     *
     * @param token {@String}
     * @return {@Link String}
     */
/*    public String extractUsername(final String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     *
     * @param token {@Link String}
     * @param userDetails {@Link UserDetails}
     * @return {@Link }
     */
 /*   public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     *
     * @param token {@Link String}
     * @return {@Link Boolean}
     */
 /*   private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     *
     * @param token {@Link String}
     * @return Date {@Link Date}
     */
 /*   private Date extractExpiration(final String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    /**
     * generateToken
     * @return String {@Link String}
     */
 /*   public String generateToken() {
        return buildToken(this.jwtExpiriaton);
    }

    private String buildToken(final Long expiration) {
        return Jwts.builder()
                .id(this.USER_ID)
                .claims(Map.of("name", this.USER_NAME))
                .subject(this.USER_NAME)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        final byte[] bytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}*/
