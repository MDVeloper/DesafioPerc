package com.desafioPerc.ms_main.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

/**
 * DTO Request file
 */
@Builder
@Getter
@Setter
public class RqTaskDto {
    @NotBlank
    @Size(max = 80)
    private String name;

    private String description;
    private Optional<Boolean> completed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-3")
    private Optional<Date> dueDate;
}
