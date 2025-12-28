package com.ArcadeBoot.WaterTask.service.impl;

import com.ArcadeBoot.WaterTask.domin.entity.TaskList;
import com.ArcadeBoot.WaterTask.repository.TaskListRepository;
import com.ArcadeBoot.WaterTask.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        if (taskList.getId() != null) {
            throw new IllegalArgumentException("TaskList with id " + taskList.getId() + " already exists");
        }

        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList with title is null or empty");
        }


        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        if (null == taskList.getId()) {
            throw new IllegalArgumentException("TaskList must have an id ");
        }
        if (!Objects.equals(taskList.getId(), id)) {
            throw new IllegalArgumentException("Action not allowed !");
        }

        TaskList existing = taskListRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("TaskList with id " + id + " does not exist"));

        existing.setTitle(taskList.getTitle());
        existing.setDescription(taskList.getDescription());
        existing.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existing);
    }
}