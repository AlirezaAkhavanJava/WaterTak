package com.ArcadeBoot.WaterTask.controller;

import com.ArcadeBoot.WaterTask.domin.dto.TaskListDto;
import com.ArcadeBoot.WaterTask.domin.entity.TaskList;
import com.ArcadeBoot.WaterTask.mappers.TaskListMapper;
import com.ArcadeBoot.WaterTask.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskList().stream()
                .map(taskListMapper::toDto)
                .toList();
    }


    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList created = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(created);
    }

    @GetMapping(path = "/{taskListId}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("taskListId") UUID id) {
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }
}
