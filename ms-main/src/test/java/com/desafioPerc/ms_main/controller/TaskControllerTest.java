package com.desafioPerc.ms_main.controller;


import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.dto.request.TaskFilterRqDto;
import com.desafioPerc.ms_main.dto.response.RsTaskDto;
import com.desafioPerc.ms_main.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TaskController unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    private final Long TEST_ID = 1L;
    private final String TEST_NAME = "Matias";
    private final String TEST_DESCRIPTION = "Testing myself";
    private final Date TEST_DUE_DATE = new Date();
    private final Boolean TEST_COMPLETED = true;
    private final int TEST_PAGE = 0;
    private final int TEST_SIZE = 2;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    private MockMvc mockMvc;

    /**
     * Initialize method
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.taskController).build();
    }

    /**
     * GetTaskByPredicate
     */
    @Test
    public void whenGetTaskByPredicateThenReturnListOfRsTaskDto() {
        final TaskFilterRqDto taskFilterRqDto = buildTaskFilterRqDto();

        final List<RsTaskDto> result = this.taskController.getTaskByPredicate(taskFilterRqDto.getCompleted(),
                taskFilterRqDto.getDueDate(), this.TEST_PAGE, this.TEST_SIZE);

        assertNotNull(result);
    }

    /**
     * GetTaskBy
     */
    @Test
    public void whenGetTaskByIdThenReturnRsTaskDto() {
        final RsTaskDto rsTaskDto = buildRsTaskDto();

        when(this.taskService.getTaskById(this.TEST_ID)).thenReturn(rsTaskDto);

        final RsTaskDto result = this.taskController.getTaskById(this.TEST_ID);

        assertNotNull(result);
    }

    /**
     * SaveTask
     */
    @Test
    public void whenSaveTaskThenReturnRsTaskDto() {
        final RqTaskDto rqTaskDto = buildRqTaskDto();
        final RsTaskDto rsTaskDto = buildRsTaskDto();

        when(this.taskService.saveTask(rqTaskDto)).thenReturn(rsTaskDto);

        final RsTaskDto result = this.taskController.saveTask(rqTaskDto);

        assertNotNull(result);
    }

    /**
     * RemoveTask
     */
    @Test
    public void whenRemoveTaskThenReturnResponseEntity() {
        this.taskController.removeTask(this.TEST_ID);
        verify(this.taskService, times(1)).removeTask(this.TEST_ID);
        final ResponseEntity<String> httpCode = this.taskController.removeTask(this.TEST_ID);
        assertEquals(httpCode, ResponseEntity.ok().build());
    }

    /**
     * UpdateTask
     */
    @Test
    public void whenUpdateTaskThenReturnRsTaskDto() {
        final RqTaskDto rqTaskDto = buildRqTaskDto();
        final RsTaskDto rsTaskDto = buildRsTaskDto();

        when(this.taskService.updateTask(this.TEST_ID, rqTaskDto)).thenReturn(rsTaskDto);

        final RsTaskDto result = this.taskController.updateTask(this.TEST_ID, rqTaskDto);

        assertNotNull(result);
    }

    /**
     * UpdateCompletedTask
     */
    @Test
    public void whenUpdateCompletedTaskThenReturnResponseEntity() {
        this.taskController.updateCompletedTask(this.TEST_ID);
        verify(this.taskService, times(1)).updateCompletedTask(this.TEST_ID);
        final ResponseEntity<String> httpCode = this.taskController.updateCompletedTask(this.TEST_ID);
        assertEquals(httpCode, ResponseEntity.ok().build());
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
                .completed(Optional.of(false))
                .build();
    }
}
