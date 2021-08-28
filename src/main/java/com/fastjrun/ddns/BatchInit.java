package com.fastjrun.ddns;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BatchInit {

  public static void main(String[] args) {
    new SpringApplicationBuilder(BatchInit.class).bannerMode(Banner.Mode.CONSOLE).run(args);
  }
}
