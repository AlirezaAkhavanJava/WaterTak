package com.ArcadeBoot.WaterTask.controller;

import com.ArcadeBoot.WaterTask.domin.dto.TaskListDto;
import com.ArcadeBoot.WaterTask.mappers.TaskListMapper;
import com.ArcadeBoot.WaterTask.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
