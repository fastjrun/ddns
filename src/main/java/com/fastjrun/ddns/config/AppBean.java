package com.fastjrun.ddns.config;

import com.fastjrun.util.AliYunUtil;
import com.fastjrun.util.IpUtil;
import com.fastjrun.util.impl.IPUtilWithNetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBean {
    @Autowired
    AppConfig appConfig;

    @Bean
    public IpUtil ipUtil(){
        IPUtilWithNetService ipUtil = new IPUtilWithNetService();
        ipUtil.setLocateIPUrl(appConfig.getLocateIPUrl());
        return ipUtil;
    }

    @Bean
    public AliYunUtil aliYunUtil(){
        AliYunUtil aliYunUtil = new AliYunUtil();
        aliYunUtil.setRegionId(appConfig.getAliyun().getRegionId());
        aliYunUtil.setAccessKeyId(appConfig.getAliyun().getAccessKeyId());
        aliYunUtil.setAccessKeySecret(appConfig.getAliyun().getAccessKeySecret());
        aliYunUtil.init();
        return aliYunUtil;
    }

}
