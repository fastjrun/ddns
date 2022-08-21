package com.fastjrun.ddns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class AppServiceTest extends BaseServiceTest {
    @Autowired
    AppService appService;


    @Test
    public void testRestart() {
        appService.restart();
    }
}
