package com.desafioPerc.ms_main.exception;

import com.desafioPerc.ms_main.dto.ErrorDetailsDTO;
import com.desafioPerc.ms_main.utils.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Class to handle custom exceptions and ErrorType
 */
@ControllerAdvice
public class PercExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PercExceptionHandler.class);

    /**
     * Handle exception
     * @param ex {@Link Exception}
     * @param request {@Link WebRequest}
     * @return responseEntity {@Link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDTO> handleInternalServerErrorException(final Exception ex, final WebRequest request) {
        return createErrorReponse(ex, ErrorType.TASK_GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle resource not found
     * @param ex {@Link Exception}
     * @param request {@Link WebRequest}
     * @return responseEntity {@Link ResponseEntity}
     */
    @ExceptionHandler(PercException.class)
    public ResponseEntity<ErrorDetailsDTO> handleInternalServerErrorException(final PercException ex, final WebRequest request) {
        return createErrorReponse(ex, ex.getError(), HttpStatus.valueOf(ex.getHttpStatus()));
    }

    /**
     * Handle service exception
     * @param ex {@Link Exception}
     * @param request {@Link WebRequest}
     * @return responseEntity {@Link ResponseEntity}
     */
    @ExceptionHandler(PercServiceException.class)
    public ResponseEntity<ErrorDetailsDTO> handleInternalServerErrorException(final PercServiceException ex, final WebRequest request) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getError().equals(ErrorType.TASK_EMPTY_INPUT_DATA)) {
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return createErrorReponse(ex, ex.getError(), statusCode);
    }

    private ResponseEntity<ErrorDetailsDTO> createErrorReponse(final Exception ex, final ErrorType error, final HttpStatus httpStatus) {
        LOGGER.error("Error: [{}]", error, ex);
        final ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(error, ex.getMessage());
        return new ResponseEntity<>(errorDetailsDTO, httpStatus);
    }
}
