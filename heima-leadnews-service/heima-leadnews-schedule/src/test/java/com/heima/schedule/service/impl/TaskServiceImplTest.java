package com.heima.schedule.service.impl;

import com.heima.model.schedule.dtos.Task;
import com.heima.schedule.ScheduleApplication;
import com.heima.schedule.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService service;

    @Test
    public void addTask() {
        Task task = new Task();
        task.setParameters("parameters".getBytes());
        task.setPriority(80);
        task.setExecuteTime((new Date().getTime()) + 1000);
        task.setTaskType(11);

        System.out.println(service.addTask(task));
    }
}