package com.fastjrun.ddns;

import com.fastjrun.ddns.task.CheckIPTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class BatchInit {

  public static void main(String[] args) {
    new SpringApplicationBuilder(BatchInit.class).bannerMode(Banner.Mode.CONSOLE).run(args);
  }
}
