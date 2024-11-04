package com.desafioPerc.ms_main.utils;

/**
 * Error types/codes
 */
public enum ErrorType {
    // Task Errors
    TASK_GENERIC_ERROR("ms_main_001", "[MS-MAIN] Unexpected error"),
    TASK_INTERNAL_ERROR("ms_main_002", "[MS-MAIN] Internal unexpected error"),
    TASK_BAD_REQUEST("ms_main_003", "[MS-MAIN] Bad request"),
    TASK_EMPTY_INPUT_DATA("ms_main_004", "[MS-MAIN] Input data is empty"),
    TASK_RESOURCE_NOT_FOUND("ms_main_004", "[MS-MAIN] Resource not found");

    private final String code;
    private final String description;

    private ErrorType(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     *
     * @return code {@Link String}
     */
    public String getCode() {
        return this.code;
    }

    /**
     *
     * @return code {@Link String}
     */
    public String getDescription() {
        return this.description;
    }
}
