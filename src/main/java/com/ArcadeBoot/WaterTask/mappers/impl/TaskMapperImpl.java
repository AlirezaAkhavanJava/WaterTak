package com.ArcadeBoot.WaterTask.mappers.impl;

import com.ArcadeBoot.WaterTask.domin.dto.TaskDto;
import com.ArcadeBoot.WaterTask.domin.entity.Task;
import com.ArcadeBoot.WaterTask.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.id())
                .title(taskDto.title())
                .description(taskDto.description())
                .dueDate(taskDto.dueDate())
                .status(taskDto.status())
                .priority(taskDto.priority())
                .taskList(null)
                .created(null)
                .updated(null)
                .build();
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
