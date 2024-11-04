package com.desafioPerc.ms_main.service.impl;

import com.desafioPerc.ms_main.converter.ConvertRqTaskDtoToTask;
import com.desafioPerc.ms_main.converter.ConvertTaskToRsTaskDto;
import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.dto.request.TaskFilterRqDto;
import com.desafioPerc.ms_main.dto.response.RsTaskDto;
import com.desafioPerc.ms_main.entity.QTask;
import com.desafioPerc.ms_main.entity.Task;
import com.desafioPerc.ms_main.exception.PercServiceException;
import com.desafioPerc.ms_main.repository.TaskRepository;
import com.desafioPerc.ms_main.service.TaskService;
import com.desafioPerc.ms_main.utils.ErrorType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the service for Task
 */
@Service
public class TaskServiceImpl implements TaskService {


    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final ConvertRqTaskDtoToTask convertRqTaskDtoToTask;
    private final ConvertTaskToRsTaskDto convertTaskToRsTaskDto;


    /**
     * Constructor
     * @param taskRepository {@Link TaskRepository}
     * @param convertRqTaskDtoToTask {@Link ConvertRqTaskDtoToTask}
     * @param convertTaskToRsTaskDto {@Link ConvertTaskToRsTaskDto}
     */
    @Autowired
    public TaskServiceImpl(final TaskRepository taskRepository, final  ConvertRqTaskDtoToTask convertRqTaskDtoToTask,
                           final  ConvertTaskToRsTaskDto convertTaskToRsTaskDto) {
        this.taskRepository = taskRepository;
        this.convertRqTaskDtoToTask = convertRqTaskDtoToTask;
        this.convertTaskToRsTaskDto = convertTaskToRsTaskDto;
    }

    @Override
    public List<RsTaskDto> getTaskByPredicate(final TaskFilterRqDto taskFilterRqDto, final int page, final int size) {
        LOGGER.info("Finding task with filter: {}", taskFilterRqDto);
        final Predicate filterPredicate = buildPredicate(taskFilterRqDto);
        final List<RsTaskDto> results = new ArrayList<>();

        try {
            final Page<Task> pagesTask = this.taskRepository.findAll(filterPredicate, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
            pagesTask.forEach(element -> results.add(this.convertTaskToRsTaskDto.convert(element)));
        } catch (NoResultException e) {
            throw new PercServiceException("Somethings goes wrong at getTaskByPredicate", ErrorType.TASK_INTERNAL_ERROR);
        }
        return results;
    }

    @Override
    public RsTaskDto getTaskById(final Long id) {
        LOGGER.info("Finding task with id: {}", id);
        final Optional<Task> task = this.taskRepository.findById(id);
        return this.convertTaskToRsTaskDto.convert(task.orElseThrow(() ->
                new PercServiceException("Could not find requested task", ErrorType.TASK_RESOURCE_NOT_FOUND)));
    }

    @Transactional
    @Override
    public RsTaskDto saveTask(final RqTaskDto rqTaskDto) {
        LOGGER.info("Creating a new task with: {}", rqTaskDto);
        final Task task = this.convertRqTaskDtoToTask.convert(rqTaskDto);
        try {
            return this.convertTaskToRsTaskDto.convert(this.taskRepository.save(task));
        } catch (NoResultException e) {
            throw new PercServiceException("Somethings goes wrong at saveTask", ErrorType.TASK_INTERNAL_ERROR);
        }
    }

    @Override
    public void removeTask(final Long id) {
        LOGGER.info("Removing task with id: {}", id);
        final Optional<Task> task = this.taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new PercServiceException("Task not found in order to delete it.", ErrorType.TASK_RESOURCE_NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }

    @Transactional
    @Override
    public RsTaskDto updateTask(final Long id, final RqTaskDto rqTaskDto) {
        final Optional<Task> task = this.taskRepository.findById(id);

        if (!task.isPresent()) {
            throw new PercServiceException("Task not found in order to update it.", ErrorType.TASK_RESOURCE_NOT_FOUND);
        } else {
            task.get().setName(rqTaskDto.getName());
            task.get().setCompleted(rqTaskDto.getCompleted().get());
            task.get().setDescription(rqTaskDto.getDescription());
            task.get().setDueDate(rqTaskDto.getDueDate().get());
        }

        return this.convertTaskToRsTaskDto.convert(this.taskRepository.save(task.get()));
    }


    @Override
    public void updateCompletedTask(final Long id) {
        final Optional<Task> task = this.taskRepository.findById(id);

        if (!task.isPresent()) {
            throw new PercServiceException("Task not found in order to change hes complete value.", ErrorType.TASK_RESOURCE_NOT_FOUND);
        } else {
            if (task.get().isCompleted()) {
                task.get().setCompleted(false);
            } else {
                task.get().setCompleted(true);
            }
        }

        this.taskRepository.save(task.get());
    }

    private Predicate buildPredicate(final TaskFilterRqDto filters) {
        final BooleanBuilder predicate = new BooleanBuilder();
        final QTask qTask = QTask.task;

        if (filters.getCompleted().isPresent()) {
            predicate.and(qTask.completed.eq(filters.getCompleted().get()));
        }

        if (filters.getDueDate() != null) {
            predicate.and(qTask.dueDate.eq(filters.getDueDate()));
        }

        return predicate;
    }
}
