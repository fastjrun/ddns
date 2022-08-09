package com.fastjrun.util;

import com.fastjrun.util.impl.IPUtilWithNetService;
import org.junit.Before;
import org.junit.Test;

public class IPUtilWithNetServiceTest {
  IPUtilWithNetService ipUtilWithNetService;

  @Before
  public void init() {
    ipUtilWithNetService = new IPUtilWithNetService();
    ipUtilWithNetService.setLocateIPUrl("http://ip.cuiyingfeng.com");
  }

  @Test
  public void testLocateWanIP() {
    String wanIP = ipUtilWithNetService.locateWanIP();
    System.out.println(wanIP);
  }
}
