package com.fastjrun.service;

import com.fastjrun.ddns.BatchInit;
import com.fastjrun.ddns.service.DomainRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchInit.class})
public class DomainRecordServiceTest {
  @Autowired DomainRecordService domainRecordService;

  @Test
  public void testQueryATypeDomainRecordId() {
    String recordId = domainRecordService.queryDomainRecordId();
    System.out.println(recordId);
  }
}
