package com.fastjrun.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface IpUtil {

  String locateWanIP();

  default String getIPByDomain(String domain) {
    try {
      InetAddress myServer = InetAddress.getByName(domain);
      return myServer.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return "";
  }
}
