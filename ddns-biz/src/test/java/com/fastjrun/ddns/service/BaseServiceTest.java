package com.fastjrun.ddns.service;

import com.fastjrun.ddns.config.AppConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest
@Import({AppConfig.class})
public class BaseServiceTest extends AbstractTestNGSpringContextTests {

}
