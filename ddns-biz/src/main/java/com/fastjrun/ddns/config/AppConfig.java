package com.fastjrun.ddns.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.fastjrun.ddns")
@MapperScan({"com.fastjrun.ddns.dao"})
@Component
public class AppConfig {

    public final static String LOCATEIP_URL = "locateIPUrl";
    public final static String ACCESSKEY_ID = "accessKeyId";
    public final static String ACCESSKEY_SECRET = "accessKeySecret";
    public final static String CONFIG_DOMAIN = "configDomain";
    public final static String INTERVAL = "interval";
}
