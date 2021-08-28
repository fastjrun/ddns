package com.fastjrun.util.impl;

import com.fastjrun.util.AbstractIPUtil;
import com.fastjrun.util.IpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class IPUtilWithIPCUIYINGFENG extends AbstractIPUtil implements IpUtil {

  @Override
  public String locateWanIP() {
    InputStream ins = null;
    try {
      URL url = new URL("http://ip.cuiyingfeng.com");
      URLConnection con = url.openConnection();
      ins = con.getInputStream();
      InputStreamReader isReader = new InputStreamReader(ins, Charset.defaultCharset());
      BufferedReader bReader = new BufferedReader(isReader);
      StringBuilder webContent = new StringBuilder();
      String str = null;
      while ((str = bReader.readLine()) != null) {
        webContent.append(str);
      }
      return webContent.toString();
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