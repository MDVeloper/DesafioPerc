package com.desafioPerc.ms_main.service;

import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.dto.request.TaskFilterRqDto;
import com.desafioPerc.ms_main.dto.response.RsTaskDto;

import java.util.List;

/**
 * Represents the service for Task
 */
public interface TaskService {

    /**
     * Get a Task by a given ID
     * @param filters {@Link TaskFilterRqDto}
     * @param page {@Link int}
     * @param size {@Link int}
     * @return RsTaskDto {@link RsTaskDto}
     */
    public List<RsTaskDto> getTaskByPredicate(final TaskFilterRqDto filters, final int page, final int size);

    /**
     * Get a Task by a given ID
     * @param id {@Link Long}
     * @return RsTaskDto {@link RsTaskDto}
     */
    public RsTaskDto getTaskById(final Long id);

    /**
     * Persist a new task
     * @param rqTaskDto {@link RqTaskDto}
     * @return RsTaskDto {@link RsTaskDto}
     */
    public RsTaskDto saveTask(final RqTaskDto rqTaskDto);


    /**
     * Remove a Task by a given ID
     * @param id {@link Long}
     */
    public void removeTask(final Long id);

    /**
     * Update a Task by a given ID
     * @param id {@link Long}
     * @param rqTaskDto {@link RqTaskDto}
     * @return RsTaskDto {@link RsTaskDto}
     */
    public RsTaskDto updateTask(final Long id, final RqTaskDto rqTaskDto);

    /**
     * Update a Task by a given ID
     * @param id {@link Long}
     */
    public void updateCompletedTask(final Long id);
}
