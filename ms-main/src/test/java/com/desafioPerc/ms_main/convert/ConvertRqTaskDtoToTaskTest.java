package com.desafioPerc.ms_main.convert;

import com.desafioPerc.ms_main.converter.ConvertRqTaskDtoToTask;
import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.entity.Task;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * ConvertRqTaskDtoToTaskTest
 */
public class ConvertRqTaskDtoToTaskTest {

    private final String TEST_NAME = "Matias";
    private final String TEST_DESCRIPTION = "Testing myself";
    private final Optional<Date> TEST_DUE_DATE = Optional.of(new Date());
    private final Optional<Boolean> TEST_COMPLETED = Optional.of(true);

    /**
     * when a RqTaskDto is converted To a Task
     */
    @Test
    public void whenConvertRqTaskDtoToTask() {
        final RqTaskDto rqTaskDto = RqTaskDto.builder()
                .name(this.TEST_NAME)
                .description(this.TEST_DESCRIPTION)
                .dueDate(this.TEST_DUE_DATE)
                .completed(this.TEST_COMPLETED)
                .build();

        final ConvertRqTaskDtoToTask convertRqTaskDtoToTask = new ConvertRqTaskDtoToTask();
        final Task task = convertRqTaskDtoToTask.convert(rqTaskDto);

        assertNotNull(task);
        assertEquals(rqTaskDto.getName(), task.getName());
        assertEquals(rqTaskDto.getDescription(), task.getDescription());
        assertEquals(rqTaskDto.getDueDate().get(), task.getDueDate());
        assertEquals(rqTaskDto.getCompleted().get(), task.isCompleted());
    }
}
