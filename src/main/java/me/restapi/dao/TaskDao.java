package me.restapi.dao;

import me.restapi.model.Task;

import java.util.UUID;

public interface TaskDao {

    UUID createTask(Task task);
    Task getTask(UUID id);
    void updateTask(Task task);

}
