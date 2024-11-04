package com.desafioPerc.ms_main.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TaskFilterRqDto {
    private Optional<Boolean> completed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-3")
    private Date dueDate;
}
