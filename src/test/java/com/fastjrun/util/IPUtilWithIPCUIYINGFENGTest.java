package com.fastjrun.util;

import com.fastjrun.util.impl.IPUtilWithIPCUIYINGFENG;
import org.junit.Before;
import org.junit.Test;

public class IPUtilWithIPCUIYINGFENGTest {
  IPUtilWithIPCUIYINGFENG ipUtilWithIPCUIYINGFENG;

  @Before
  public void init() {
    ipUtilWithIPCUIYINGFENG = new IPUtilWithIPCUIYINGFENG();
  }

  @Test
  public void testLocateWanIP() {
    String wanIP = ipUtilWithIPCUIYINGFENG.locateWanIP();
    System.out.println(wanIP);
  }
}
