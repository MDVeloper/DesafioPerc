package com.desafioPerc.ms_main.service;

import com.desafioPerc.ms_main.converter.ConvertRqTaskDtoToTask;
import com.desafioPerc.ms_main.converter.ConvertTaskToRsTaskDto;
import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.dto.request.TaskFilterRqDto;
import com.desafioPerc.ms_main.dto.response.RsTaskDto;
import com.desafioPerc.ms_main.entity.QTask;
import com.desafioPerc.ms_main.entity.Task;
import com.desafioPerc.ms_main.repository.TaskRepository;
import com.desafioPerc.ms_main.service.impl.TaskServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TaskService unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    private final Long TEST_ID = 1L;
    private final String TEST_NAME = "Matias";
    private final String TEST_DESCRIPTION = "Testing myself";
    private final Date TEST_DUE_DATE = new Date();
    private final Boolean TEST_COMPLETED = true;
    private final int TEST_PAGE = 0;
    private final int TEST_SIZE = 2;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ConvertRqTaskDtoToTask convertRqTaskDtoToTask;

    @Mock
    private ConvertTaskToRsTaskDto convertTaskToRsTaskDto;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    private MockMvc mockMvc;

    /**
     * Initialize method
     */
    @Before
    public void setUp() {
    }

    /**
     * getTaskByPredicate
     */
    @Test
    public void whenGetTaskByPredicateThenReturnOK() {

        final TaskFilterRqDto taskFilterRqDto = buildTaskFilterRqDto();
        final Predicate filterPredicate = buildPredicate(taskFilterRqDto);
        final Pageable pageable = PageRequest.of(this.TEST_PAGE, this.TEST_SIZE, Sort.by(Sort.Direction.ASC, "id"));
        final Page<Task> pagesTask = mock(Page.class);

        when(this.taskRepository.findAll(any(Predicate.class), any(PageRequest.class))).thenReturn(pagesTask);

        final List<RsTaskDto> result = this.taskServiceImpl.getTaskByPredicate(taskFilterRqDto, this.TEST_PAGE, this.TEST_SIZE);
        verify(this.taskRepository, times(1)).findAll(filterPredicate, pageable);

        assertNotNull(result);
        verify(this.taskRepository).findAll(any(Predicate.class), any(PageRequest.class));
    }

    /**
     * GetTaskById
     */
    @Test
    public void whenGetTaskByIdThenReturnOK() {
        final Optional<Task> task = Optional.of(buildTask());
        final RsTaskDto rsTaskDto = buildRsTaskDto();

        when(this.taskRepository.findById(this.TEST_ID)).thenReturn(task);
        when(this.convertTaskToRsTaskDto.convert(task.get())).thenReturn(rsTaskDto);

        final RsTaskDto result = this.taskServiceImpl.getTaskById(this.TEST_ID);

        assertEquals(this.TEST_ID, result.getId());
    }

    /**
     * saveTask
     */
    @Test
    public void whenSaveTaskThenReturnOK() {
        final Task task = buildTask();
        final RqTaskDto rqTaskDto = buildRqTaskDto();
        final RsTaskDto rsTaskDto = buildRsTaskDto();

        when(this.convertRqTaskDtoToTask.convert(rqTaskDto)).thenReturn(task);
        when(this.taskRepository.save(task)).thenReturn(task);
        when(this.convertTaskToRsTaskDto.convert(task)).thenReturn(rsTaskDto);

        final RsTaskDto result = this.taskServiceImpl.saveTask(rqTaskDto);

        assertNotNull(result);
    }

    /**
     * removeTask
     */
    @Test
    public void whenRemoveTaskThenReturnOK() {
        final Optional<Task> task = Optional.ofNullable(buildTask());
        when(this.taskRepository.findById(this.TEST_ID)).thenReturn(task);
        this.taskServiceImpl.removeTask(this.TEST_ID);
        verify(this.taskRepository, times(1)).deleteById(this.TEST_ID);
    }

    /**
     * updateTask
     */
    @Test
    public void whenUpdateTaskThenReturnOK() {
        final Optional<Task> task = Optional.ofNullable(buildTask());
        final RqTaskDto rqTaskDto = buildRqTaskDto();
        final RsTaskDto rsTaskDto = buildRsTaskDto();

        when(this.taskRepository.findById(this.TEST_ID)).thenReturn(task);
        when(this.taskRepository.save(task.get())).thenReturn(task.get());
        when(this.convertTaskToRsTaskDto.convert(task.get())).thenReturn(rsTaskDto);

        final RsTaskDto result = this.taskServiceImpl.updateTask(this.TEST_ID, rqTaskDto);

        assertNotNull(result);
    }

    /**
     * updateCompletedTask
     */
    @Test
    public void whenUpdateCompletedTaskThenReturnOK() {
        final Optional<Task> task = Optional.ofNullable(buildTask());
        when(this.taskRepository.findById(this.TEST_ID)).thenReturn(task);
        this.taskServiceImpl.updateCompletedTask(this.TEST_ID);
        verify(this.taskRepository, times(1)).save(task.get());
    }

    private Task buildTask() {
        return Task.builder()
                .id(this.TEST_ID)
                .name(this.TEST_NAME)
                .description(this.TEST_DESCRIPTION)
                .dueDate(this.TEST_DUE_DATE)
                .completed(this.TEST_COMPLETED)
                .build();
    }

    private RsTaskDto buildRsTaskDto() {
        return RsTaskDto.builder()
                .id(this.TEST_ID)
                .name(this.TEST_NAME)
                .description(this.TEST_DESCRIPTION)
                .dueDate(this.TEST_DUE_DATE)
                .completed(this.TEST_COMPLETED)
                .build();
    }

    private RqTaskDto buildRqTaskDto() {
        return RqTaskDto.builder()
                .name(this.TEST_NAME)
                .description(this.TEST_DESCRIPTION)
                .dueDate(Optional.of(this.TEST_DUE_DATE))
                .completed(Optional.of(this.TEST_COMPLETED))
                .build();
    }

    private TaskFilterRqDto buildTaskFilterRqDto() {
        return TaskFilterRqDto.builder()
                .completed(Optional.of(true))
                .dueDate(this.TEST_DUE_DATE)
                .build();
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
