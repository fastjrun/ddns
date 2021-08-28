package com.fastjrun.ddns.config;

import com.fastjrun.ddns.task.CheckIPTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleConfig {

  @Autowired CheckIPTask checkIPTask;

  @Scheduled(cron = "#{appConfig.checkIPTask.cron}")
  public void checkIP() {
    checkIPTask.process();
  }
}
