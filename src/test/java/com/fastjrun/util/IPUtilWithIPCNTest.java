package com.fastjrun.util;

import com.fastjrun.util.impl.IPUtilWithIPCN;
import org.junit.Before;
import org.junit.Test;

public class IPUtilWithIPCNTest {
    IPUtilWithIPCN ipUtilWithIPCN;

    @Before
    public void init() {
        ipUtilWithIPCN = new IPUtilWithIPCN();
    }

    @Test
    public void testLocateWanIP(){
        String wanIP = ipUtilWithIPCN.locateWanIP();
        System.out.println(wanIP);
    }
}
