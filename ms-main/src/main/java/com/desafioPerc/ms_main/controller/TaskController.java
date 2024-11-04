package com.desafioPerc.ms_main.controller;

import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.dto.request.TaskFilterRqDto;
import com.desafioPerc.ms_main.dto.response.RsTaskDto;
import com.desafioPerc.ms_main.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Represents the controller for Tasks
 */
@Tag(name = "Tasks", description = "Tasks")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    /**
     * Constructor
     * @param taskService {@link TaskService}
     */
    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get all Task with a filter
     * @param completed {@Link Boolean}
     * @param dueDate {@Link Date}
     * @param page {@Link int}
     * @param size {@Link int}
     * @return RsTaskDto {@link RsTaskDto}
     */
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all Task with a filter", responses = {
            @ApiResponse(description = "Get all Task with a filter", responseCode = "200", content = @Content(mediaType = "application/json"))})
    @ResponseStatus(HttpStatus.OK)
    public List<RsTaskDto> getTaskByPredicate(@RequestParam(required = false) final Optional<Boolean> completed,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                              final Date dueDate,
                                              @RequestParam(name = "page", defaultValue = "0") final int page,
                                              @RequestParam(name = "size", defaultValue = "500") final int size) {
        final TaskFilterRqDto taskFilterRqDto = new TaskFilterRqDto(completed, dueDate);
        return this.taskService.getTaskByPredicate(taskFilterRqDto, page, size);
    }

    /**
     * Get a Task by a given ID
     * @param id {@Link Long}
     * @return RsTaskDto {@link RsTaskDto}
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a Task by a given ID", responses = {
            @ApiResponse(description = "Get a Task by a given ID", responseCode = "200", content = @Content(mediaType = "application/json"))})
    @ResponseStatus(HttpStatus.OK)
    public RsTaskDto getTaskById(final @PathVariable Long id) {
        return this.taskService.getTaskById(id);
    }


    /**
     * Save a new Task
     * @param rqTaskDto {@link RqTaskDto}
     * @return RsTaskDto {@link RsTaskDto}
     */
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persist a new task", responses = {
            @ApiResponse(description = "persist a new task", responseCode = "200", content = @Content(mediaType = "application/json"))})
    public RsTaskDto saveTask(final @RequestBody @Valid RqTaskDto rqTaskDto) {
        return this.taskService.saveTask(rqTaskDto);
    }

    /**
     * Remove a Task by a given ID
     * @param id {@link Long}
     * @return returns the HTTP status code
     */
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Remove a task by a given ID", responses = {
            @ApiResponse(description = "persist a new task", responseCode = "200", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<String> removeTask(final @PathVariable Long id) {
        this.taskService.removeTask(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update a Task by a given ID
     * @param id {@link Long}
     * @param rqTaskDto {@link RqTaskDto}
     * @return RsTaskDto {@link RsTaskDto}
     */
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "update a persited task", responses = {
            @ApiResponse(description = "update a persited task", responseCode = "200", content = @Content(mediaType = "application/json"))})
    public RsTaskDto updateTask(@PathVariable final Long id, final @RequestBody @Valid RqTaskDto rqTaskDto) {
        return this.taskService.updateTask(id, rqTaskDto);
    }

    /**
     * Update the value 'completed' of a Task by a given ID
     * @param id {@link Long}
     * @return returns the HTTP status code
     */
    @PatchMapping(path = "/{id}")
    @Operation(summary = "Update the value 'completed' of a Task by a given ID", responses = {
            @ApiResponse(description = "Update the value 'completed' of a Task by a given ID",
                    responseCode = "200", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<String> updateCompletedTask(@PathVariable final Long id) {
        this.taskService.updateCompletedTask(id);
        return ResponseEntity.ok().build();
    }
}
