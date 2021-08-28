package com.fastjrun.ddns.config;

import lombok.Data;

@Data
public class AliyunConfig {

  private String regionId = "cn-hangzhou";

  private String accessKeyId = "";

  private String accessKeySecret = "";
}
