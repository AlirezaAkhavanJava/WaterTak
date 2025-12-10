package com.ArcadeBoot.WaterTask.repository;

import com.ArcadeBoot.WaterTask.domin.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
