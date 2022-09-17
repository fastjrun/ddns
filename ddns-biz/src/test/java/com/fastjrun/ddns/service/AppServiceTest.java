package com.fastjrun.ddns.service;

import com.fastjrun.ddns.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class AppServiceTest extends BaseServiceTest {
    @Autowired
    AppService appService;


    @Test
    public void testRestart() {
        AppService appService= SpringContextUtils.getBean(AppService.class);
        appService.restart();
    }
}
