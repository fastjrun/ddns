package com.fastjrun.util;

import org.junit.Before;
import org.junit.Test;

public class AliYunUtilTest {
    AliYunUtil aliYunUtil;

    @Before
    public void init() {
        aliYunUtil = new AliYunUtil();
        aliYunUtil.setAccessKeyId("werew");
        aliYunUtil.setAccessKeySecret("werwerwerw");
        aliYunUtil.init();
    }

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

}
