package com.fastjrun.client;

import org.junit.Before;
import org.junit.Test;

public class AliYunClientTest {

    AliYunClient aliYunClient;

    @Before
    public void init() {
        aliYunClient = new AliYunClient();
        aliYunClient.setAccessKeyId("accid");
        aliYunClient.setAccessKeySecret("secret");
        aliYunClient.init();

    }

    @Test
    public void testAddDomainRecord() {
        String domainName = "fastjrun.com";
        String ip = "127.0.0.1";
        String type = "A";
        String rR = "test22";
        String recordId = aliYunClient.addDomainRecord(domainName, ip, rR, type);
        System.out.println(recordId);
    }

    @Test
    public void testUpdateATypeDomainRecord() {
        String recordId = "rwrw";
        String ip = "127.0.0.3";
        String type = "A";
        String rR = "test22";
        boolean res = aliYunClient.updateDomainRecord(recordId, ip, rR, type);
        System.out.println(res);
    }

    @Test
    public void testQueryATypeDomainRecordId() {
        String configDomain = "fastjrun.com";
        String rR = "test22";
        String recordId = aliYunClient.queryATypeDomainRecordId(configDomain, rR).getRecordId();
        System.out.println(recordId);
    }
}
