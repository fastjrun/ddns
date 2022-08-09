package com.fastjrun.util;

import com.alibaba.dcm.DnsCacheManipulator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface IpUtil {

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
