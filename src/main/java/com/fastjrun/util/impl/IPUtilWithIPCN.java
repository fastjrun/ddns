package com.fastjrun.util.impl;

import com.fastjrun.util.AbstractIPUtil;
import com.fastjrun.util.IpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IPUtilWithIPCN extends AbstractIPUtil implements IpUtil {

  @Override
  public String locateWanIP() {
    InputStream ins = null;
    try {
      URL url = new URL("https://ip.cn/api/index?ip=&type=0");
      URLConnection con = url.openConnection();
      ins = con.getInputStream();
      InputStreamReader isReader = new InputStreamReader(ins, "UTF-8");
      BufferedReader bReader = new BufferedReader(isReader);
      StringBuffer webContent = new StringBuffer();
      String str;
      while ((str = bReader.readLine()) != null) {
        webContent.append(str);
      }
      Map<String, String> map = new HashMap<>(10);
      Arrays.stream(webContent.toString().replaceAll("\"", "").split(","))
          .forEach(
              var -> {
                String[] keyValue = var.split(":");
                map.put(keyValue[0], keyValue[1]);
              });
      return map.get("ip");
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
