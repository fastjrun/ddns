package com.fastjrun.client;

import com.alibaba.dcm.DnsCacheManipulator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface IpClient {

    String locateWanIP();

    default String getIPByDomain(String domain) {
        // 清除缓存
        DnsCacheManipulator.removeDnsCache(domain);
        // 根据域名获取IP
        try {
            InetAddress myServer = InetAddress.getByName(domain);
            return myServer.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}
