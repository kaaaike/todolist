package com.kaike.study.todolist.task;

import com.kaike.study.todolist.user.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID idUser);
}
