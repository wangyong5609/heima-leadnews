package com.heima.schedule.service.impl;

import com.heima.model.schedule.dtos.Task;
import com.heima.schedule.ScheduleApplication;
import com.heima.schedule.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService service;

    @Test
    public void addTask() {
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setParameters("parameters".getBytes());
            task.setPriority(80);
            task.setExecuteTime(System.currentTimeMillis() + 1000 * i);
            task.setTaskType(i);
            System.out.println(service.addTask(task));
        }
    }

    @Test
    public void cancelTask() {
        service.cancelTask(1718981966196105218L);
    }

    @Test
    public void pollTask() {
        Task poll = service.poll(11, 80);
        System.out.println(poll);
    }
}