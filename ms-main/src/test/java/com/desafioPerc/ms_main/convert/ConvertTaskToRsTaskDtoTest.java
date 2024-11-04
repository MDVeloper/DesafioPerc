package com.desafioPerc.ms_main.convert;

import com.desafioPerc.ms_main.converter.ConvertTaskToRsTaskDto;
import com.desafioPerc.ms_main.dto.response.RsTaskDto;
import com.desafioPerc.ms_main.entity.Task;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * ConvertTaskToRsTaskDtoTest
 */
public class ConvertTaskToRsTaskDtoTest {

    private final Long TEST_ID = 1L;
    private final String TEST_NAME = "Matias";
    private final String TEST_DESCRIPTION = "Testing myself";
    private final Date TEST_DUE_DATE = new Date();
    private final Boolean TEST_COMPLETED = true;

    /**
     * when a Task is converted To a RsTaskDto
     */
    @Test
    public void whenConvertTaskToRsTaskDto() {
        final Task task = Task.builder()
                .id(this.TEST_ID)
                .name(this.TEST_NAME)
                .description(this.TEST_DESCRIPTION)
                .dueDate(this.TEST_DUE_DATE)
                .completed(this.TEST_COMPLETED)
                .build();

        final ConvertTaskToRsTaskDto convertTaskToRsTaskDto = new ConvertTaskToRsTaskDto();
        final RsTaskDto rsTaskDto = convertTaskToRsTaskDto.convert(task);

        assertNotNull(rsTaskDto);
        assertEquals(rsTaskDto.getName(), task.getName());
        assertEquals(rsTaskDto.getDescription(), task.getDescription());
        assertEquals(rsTaskDto.getDueDate(), task.getDueDate());
        assertEquals(rsTaskDto.getCompleted(), task.isCompleted());
    }
}
