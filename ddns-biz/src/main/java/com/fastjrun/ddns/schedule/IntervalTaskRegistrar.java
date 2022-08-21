package com.fastjrun.ddns.schedule;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IntervalTaskRegistrar implements DisposableBean {

    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);


    @Autowired
    TaskScheduler taskScheduler;

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(10);
        scheduler.initialize();
        return scheduler;
    }

    public void addIntervalTask(Runnable task, long interval) {
        addIntervalTask(new IntervalTask(task, interval));
    }

    public void addIntervalTask(IntervalTask intervalTask) {
        if (intervalTask != null) {
            Runnable task = intervalTask.getRunnable();
            if (this.scheduledTasks.containsKey(task)) {
                removeIntervalTask(task);
            }

            this.scheduledTasks.put(task, scheduleIntervalTask(intervalTask));
        }
    }

    public void removeIntervalTask(Runnable task) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (scheduledTask != null)
            scheduledTask.cancel();
    }

    public ScheduledTask scheduleIntervalTask(IntervalTask intervalTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.scheduleAtFixedRate(intervalTask.getRunnable(), intervalTask.getInterval());

        return scheduledTask;
    }


    @Override
    public void destroy() {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }

        this.scheduledTasks.clear();
    }
}
