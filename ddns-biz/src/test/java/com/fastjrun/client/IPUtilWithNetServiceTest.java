package com.fastjrun.client;

import com.fastjrun.client.impl.IPClientWithNetService;
import org.junit.Before;
import org.junit.Test;

public class IPUtilWithNetServiceTest {
    IpClient ipClient;

    @Before
    public void init() {
        IPClientWithNetService ipClientWithNetService = new IPClientWithNetService();
        ipClientWithNetService.setLocateIPUrl("http://ip.cuiyingfeng.com");
        ipClient = ipClientWithNetService;
    }

    @Test
    public void testLocateWanIP() {
        String wanIP = ipClient.locateWanIP();
        System.out.println(wanIP);
    }
}
