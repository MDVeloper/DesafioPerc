package com.desafioPerc.ms_main.dto;

import com.desafioPerc.ms_main.utils.ErrorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represent the error details dto
 */
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetailsDTO {
    private String code;
    private String message;
    private String errorDescription;

    /**
     *
     * @param error {@Link ErrorType}
     * @param message {@Link String}
     */
    public ErrorDetailsDTO(final ErrorType error, final String message) {
        this.code = error.getCode();
        this.message = message;
        this.errorDescription = error.getDescription();
    }
}
