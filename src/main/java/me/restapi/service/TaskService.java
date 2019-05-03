package me.restapi.service;

import me.restapi.dao.TaskDao;
import me.restapi.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public UUID createTask() {

        Task task = new Task();

        taskCreated(task);
        UUID taskId = taskDao.createTask(task);

        taskRunning(task, taskId);
        taskFinished(task, taskId);

        return taskId;
    }

    public Task getTask(UUID id) {
        return taskDao.getTask(id);
    }

    @Async
    public void taskRunning(Task task, UUID taskId) {

        task.setStatus("running");
        task.setTimestamp(dateTimeIsoFormat());
        task.setUuid(taskId);

        taskDao.updateTask(task);
    }

    @Async
    public void taskFinished(Task task, UUID taskId) {

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                task.setStatus("finished");
                task.setTimestamp(dateTimeIsoFormat());
                task.setUuid(taskId);
                taskDao.updateTask(task);
                timer.cancel();
            }
        };

        int delay = 120000; // 2 minutes
        timer.schedule(timerTask, delay);

    }

    private String dateTimeIsoFormat() {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        return formatter.format(localDateTime);
    }

    private void taskCreated(Task task) {

        task.setStatus("created");
        task.setTimestamp(dateTimeIsoFormat());

    }

 }
