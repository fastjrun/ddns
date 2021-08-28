package com.fastjrun.ddns.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ddns")
@Data
public class AppConfig {
  private AliyunConfig aliyun;

  private CheckIPTaskConfig checkIPTask;
}
