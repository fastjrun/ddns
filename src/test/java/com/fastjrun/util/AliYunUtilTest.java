package com.fastjrun.util;

import com.fastjrun.ddns.BatchInit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchInit.class})
public class AliYunUtilTest {
  @Autowired AliYunUtil aliYunUtil;

  @Test
  public void testAddDomainRecord() {
    String domainName = "fastjrun.com";
    String ip = "127.0.0.1";
    String type = "A";
    String rR = "test22";
    String recordId = aliYunUtil.addDomainRecord(domainName, ip, rR, type);
    System.out.println(recordId);
  }

  @Test
  public void testUpdateATypeDomainRecord() {
    String recordId = "rwrw";
    String ip = "127.0.0.3";
    String type = "A";
    String rR = "test22";
    boolean res = aliYunUtil.updateDomainRecord(recordId, ip, rR, type);
    System.out.println(res);
  }

  @Test
  public void testQueryATypeDomainRecordId() {
    String configDomain = "fastjrun.com";
    String rR = "test22";
    String recordId = aliYunUtil.queryATypeDomainRecordId(configDomain, rR);
    System.out.println(recordId);
  }
}
