/*package com.desafioPerc.ms_main.controller;

import com.desafioPerc.ms_main.dto.response.RsTokenDto;
import com.desafioPerc.ms_main.service.impl.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents the controller for Auth
 */
/*@Tag(name = "Auth", description = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final AuthService authService;


    /**
     * Constructor
     * @param authService {@link AuthService}
     */
 /*   @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    /**
     * Get token
     * @return RsTokenDto {@link RsTokenDto}
     */
 /*   @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get token", responses = {
            @ApiResponse(description = "Get token", responseCode = "200", content = @Content(mediaType = "application/json"))})
    @ResponseStatus(HttpStatus.OK)
    public RsTokenDto getToken() {
        return this.authService.getToken();
    }

}*/
