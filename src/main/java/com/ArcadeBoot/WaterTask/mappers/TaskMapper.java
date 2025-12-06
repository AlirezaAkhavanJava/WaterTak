package com.ArcadeBoot.WaterTask.mappers;

import com.ArcadeBoot.WaterTask.domin.dto.TaskDto;
import com.ArcadeBoot.WaterTask.domin.entity.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
