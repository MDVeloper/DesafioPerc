package com.desafioPerc.ms_main.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * DTO Response file
 */
@Builder
@Getter
@Setter
public class RsTaskDto {
    private Long id;
    private String name;
    private String description;
    private Boolean completed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-3")
    private Date dueDate;

    private Date createDate;
    private Date modifyDate;
}
