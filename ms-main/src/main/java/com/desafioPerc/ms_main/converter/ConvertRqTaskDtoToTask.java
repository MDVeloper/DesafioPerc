package com.desafioPerc.ms_main.converter;


import com.desafioPerc.ms_main.dto.request.RqTaskDto;
import com.desafioPerc.ms_main.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert a RqTaskDTO to an entity Task
 */
@Component
public class ConvertRqTaskDtoToTask implements Converter<RqTaskDto, Task> {
    /**
     * Convert method
     * @param source {@link RqTaskDto}
     * @return Task {@link Task}
     */
    @Override
    public Task convert(final RqTaskDto source) {
        final Task task = Task.builder()
                .name(source.getName())
                .description(source.getDescription())
                .completed(source.getCompleted().isPresent() ? source.getCompleted().get() : false)
                .dueDate(source.getDueDate().isPresent() ? source.getDueDate().get() : null)
                .build();

        return task;
    }
}
