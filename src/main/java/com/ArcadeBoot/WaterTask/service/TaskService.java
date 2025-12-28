package com.ArcadeBoot.WaterTask.service;

import com.ArcadeBoot.WaterTask.domin.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID id);

    Task createTask(UUID tasklistId, Task task);

    Optional<Task> getTaskById(UUID tasklistId, UUID id);

    Task updateTask(UUID tasklistId, UUID taskId, Task task);

    void deleteTask(UUID tasklistId, UUID taskId);
}
