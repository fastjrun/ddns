package com.fastjrun.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class AbstractIPUtil implements IpUtil {

    @Override
    public String getIPByDomain(String domain) {
        try {
            InetAddress myServer = InetAddress.getByName(domain);
            return myServer.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

}
