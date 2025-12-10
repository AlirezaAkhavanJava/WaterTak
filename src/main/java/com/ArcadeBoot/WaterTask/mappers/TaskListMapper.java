package com.ArcadeBoot.WaterTask.mappers;

import com.ArcadeBoot.WaterTask.domin.dto.TaskListDto;
import com.ArcadeBoot.WaterTask.domin.entity.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskDto);

    TaskListDto toDto(TaskList taskList);
}
