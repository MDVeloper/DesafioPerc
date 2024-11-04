/*package com.desafioPerc.ms_main.service.impl;

import com.desafioPerc.ms_main.dto.response.RsTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Represents the service for Auth
 */
/*@Service
public class AuthService {
    private final JwtProvider jwtProvider;

    /**
     * Constructor
     * @param jwtProvider {@Link JwtProvider}
     */
/*    @Autowired
    public AuthService(final JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    /**
     *
     * @return RsTokenDto {@Link RsTokenDto}
     */
 /*   public RsTokenDto getToken() {
    //public RsTokenDto getToken(final String authHeader) {
        /*if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Bearer");
        }*/

        //final String token = authHeader.substring(7);

        /*if (jwtProvider.extractExpiration(token)) {

        }*/

 /*       final String accessToken = this.jwtProvider.generateToken();
        return RsTokenDto.builder()
                .token(accessToken)
                .build();
    }
}*/
