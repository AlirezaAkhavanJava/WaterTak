package com.ArcadeBoot.WaterTask.service.impl;


import com.ArcadeBoot.WaterTask.domin.entity.Task;
import com.ArcadeBoot.WaterTask.domin.entity.TaskList;
import com.ArcadeBoot.WaterTask.domin.entity.TaskPriority;
import com.ArcadeBoot.WaterTask.domin.entity.TaskStatus;
import com.ArcadeBoot.WaterTask.repository.TaskListRepository;
import com.ArcadeBoot.WaterTask.repository.TaskRepository;
import com.ArcadeBoot.WaterTask.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public List<Task> listTasks(UUID id) {
        return taskRepository.findByTaskListId(id);
    }

    @Override
    public Task createTask(UUID tasklistId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already exists with id: " + task.getId());
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is required");
        }

        TaskPriority taskPriority = Optional
                .ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(tasklistId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task list id " + tasklistId + " does not exist")
                );

        LocalDate now = LocalDate.now();

        Task createdTask = Task.builder()
                .id(null)
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(taskStatus)
                .priority(taskPriority)
                .taskList(taskList)
                .created(now)
                .updated(now)
                .build();

        return taskRepository.save(createdTask);
    }

    @Override
    public Optional<Task> getTaskById(UUID tasklistId, UUID id) {
        return taskRepository.findByTaskListIdAndId(tasklistId, id);
    }


    @Modifying
    @Transactional
    @Override
    public Task updateTask(UUID tasklistId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task id is required");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Id's do not match");
        }
        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task priority is required");
        }
        if (null == task.getStatus()) {
            throw new IllegalArgumentException("Task status is required");
        }

        Task existing = taskRepository.findByTaskListIdAndId(tasklistId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task list id " + tasklistId + " does not exist"));

        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setDueDate(task.getDueDate());
        existing.setPriority(task.getPriority());
        existing.setStatus(task.getStatus());
        existing.setUpdated(task.getUpdated());
        return taskRepository.save(existing);


    }

    @Transactional
    @Override
    public void deleteTask(UUID tasklistId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(tasklistId, taskId);
    }
}
