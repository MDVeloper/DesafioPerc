package com.desafioPerc.ms_main.exception;

import com.desafioPerc.ms_main.utils.ErrorType;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Perc Exception error
 */
@Getter
@Setter
public class PercServiceException extends RuntimeException {

    protected final ErrorType error;

    /**
     * Constructor
     * @param message {@Link String}
     * @param error {@Link ErrorType}
     */
    public PercServiceException(final String message, final ErrorType error) {
        super(message);
        this.error = error;
    }
}
