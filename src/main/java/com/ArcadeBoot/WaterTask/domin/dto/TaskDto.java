package com.ArcadeBoot.WaterTask.domin.dto;

import com.ArcadeBoot.WaterTask.domin.entity.TaskPriority;
import com.ArcadeBoot.WaterTask.domin.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
