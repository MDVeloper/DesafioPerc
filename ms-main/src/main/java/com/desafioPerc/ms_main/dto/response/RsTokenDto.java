package com.desafioPerc.ms_main.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO Response file
 */
@Builder
@Getter
@Setter
public class RsTokenDto {
    private String token;
}
