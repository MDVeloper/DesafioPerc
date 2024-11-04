package com.desafioPerc.ms_main.converter;


import com.desafioPerc.ms_main.dto.response.RsTaskDto;
import com.desafioPerc.ms_main.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert entity Task to a RsTaskDTO
 */
@Component
public class ConvertTaskToRsTaskDto implements Converter<Task, RsTaskDto> {
    /**
     * Convert method
     * @param source {@link Task}
     * @return RsTaskDto {@link RsTaskDto}
     */
    @Override
    public RsTaskDto convert(final Task source) {
        final RsTaskDto rsTaskDto = RsTaskDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .completed(source.isCompleted())
                .dueDate(source.getDueDate())
                .createDate(source.getCreateDate())
                .modifyDate(source.getModifyDate())
                .build();

        return rsTaskDto;
    }
}
