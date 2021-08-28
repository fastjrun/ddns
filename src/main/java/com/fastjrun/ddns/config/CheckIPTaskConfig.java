package com.fastjrun.ddns.config;

import lombok.Data;

@Data
public class CheckIPTaskConfig {

  private String configDomain;

  private String recordId;

  private String rR;

  private String cron;
}
