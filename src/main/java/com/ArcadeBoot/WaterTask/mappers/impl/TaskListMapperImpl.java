package com.ArcadeBoot.WaterTask.mappers.impl;

import com.ArcadeBoot.WaterTask.domin.dto.TaskListDto;
import com.ArcadeBoot.WaterTask.domin.entity.Task;
import com.ArcadeBoot.WaterTask.domin.entity.TaskList;
import com.ArcadeBoot.WaterTask.domin.entity.TaskStatus;
import com.ArcadeBoot.WaterTask.mappers.TaskListMapper;
import com.ArcadeBoot.WaterTask.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TaskListMapperImpl implements TaskListMapper {


    private final TaskMapper taskMapper;


    @Override
    public TaskList fromDto(TaskListDto taskDto) {
        return new TaskList(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                Optional.ofNullable(taskDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto).toList())
                        .orElse(null),
                null,
                null

        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)
        );
    }


    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks || tasks.isEmpty()) {
            return null;
        }
        long closedTasks = tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();

        return closedTasks / (double) tasks.size();
    }
}
