package com.desafioPerc.ms_main.exception;

import com.desafioPerc.ms_main.utils.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Perc Exception error
 */
@AllArgsConstructor
@Getter
@Setter
public class PercException extends RuntimeException {

    protected final ErrorType error;
    protected final int httpStatus;
}
