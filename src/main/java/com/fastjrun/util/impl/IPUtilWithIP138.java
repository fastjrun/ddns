package com.fastjrun.util.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.fastjrun.util.AbstractIPUtil;
import com.fastjrun.util.IpUtil;
@Deprecated
public class IPUtilWithIP138 extends AbstractIPUtil implements IpUtil {

    @Override
    public String locateWanIP() {
        InputStream ins = null;
        try {
            URL url = new URL("http://1212.ip138.com/ic.asp");
            URLConnection con = url.openConnection();
            ins = con.getInputStream();
            InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
            BufferedReader bReader = new BufferedReader(isReader);
            StringBuffer webContent = new StringBuffer();
            String str = null;
            while ((str = bReader.readLine()) != null) {
                webContent.append(str);
            }
            // System.out.println(webContent);
            int start = webContent.indexOf("[") + 1;
            int end = webContent.indexOf("]");
            return webContent.substring(start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
