package com.ArcadeBoot.WaterTask.controller;

import com.ArcadeBoot.WaterTask.domin.dto.TaskDto;
import com.ArcadeBoot.WaterTask.domin.entity.Task;
import com.ArcadeBoot.WaterTask.mappers.TaskMapper;
import com.ArcadeBoot.WaterTask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasklist/{task-list-id}/tasks")
public class TasksController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    @GetMapping
    public List<TaskDto> taskList(@PathVariable("task-list-id") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto getTask(@PathVariable("task-list-id") UUID taskListId,
                           @RequestBody TaskDto taskDto) {
        var createdTask =
                taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdTask);
    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task-list-id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ) {

        return taskService.getTaskById(taskListId, taskId)
                .map(taskMapper::toDto);
    }

    @PutMapping(path = "/{task-id}")
    public TaskDto updateTask(@PathVariable("task-id") UUID taskId,
                              @PathVariable("task-list-id") UUID taskListId,
                              @RequestBody TaskDto taskDto) {
        Task updated = taskService.updateTask(taskId, taskListId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updated);
    }

    public void deleteTask(@PathVariable("task-list-id") UUID taskListId, UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }
}
