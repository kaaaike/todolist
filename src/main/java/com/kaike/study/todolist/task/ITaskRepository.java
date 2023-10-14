package com.kaike.study.todolist.task;

import com.kaike.study.todolist.user.JpaRepository;

import java.util.UUID;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

}
