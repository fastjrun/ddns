package com.fastjrun.ddns;

import com.fastjrun.ddns.service.AppService;
import com.fastjrun.ddns.utils.SpringContextUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DdnsAppLication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DdnsAppLication.class)
            .bannerMode(Banner.Mode.CONSOLE)
            .run(args);
    AppService appService= SpringContextUtils.getBean(AppService.class);
    appService.restart();
    System.out.println(DdnsAppLication.class.getResource("/"));
  }
}
