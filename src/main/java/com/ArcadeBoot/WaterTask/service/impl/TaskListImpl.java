package com.ArcadeBoot.WaterTask.service.impl;

import com.ArcadeBoot.WaterTask.domin.entity.TaskList;
import com.ArcadeBoot.WaterTask.repository.TaskListRepository;
import com.ArcadeBoot.WaterTask.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskListImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }
}