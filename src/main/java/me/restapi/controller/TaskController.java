package me.restapi.controller;

import me.restapi.model.Task;
import me.restapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/task")
    public ResponseEntity<UUID> createTask() {
        return new ResponseEntity<>(taskService.createTask(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") @NotBlank String id) {

        try {
            UUID taskId = UUID.fromString(id);
            Task task = taskService.getTask(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (IncorrectResultSizeDataAccessException ex) {
            if (ex.getActualSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            throw ex;
        }

    }
}
