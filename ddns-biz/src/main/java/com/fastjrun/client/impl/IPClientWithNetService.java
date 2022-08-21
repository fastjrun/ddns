package com.fastjrun.client.impl;

import com.fastjrun.client.IpClient;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
@Data
public class IPClientWithNetService implements IpClient {

  private String locateIPUrl;

  @Override
  public String locateWanIP() {
    InputStream ins = null;
    try {
      URL url = new URL(this.locateIPUrl);
      URLConnection con = url.openConnection();
      ins = con.getInputStream();
      InputStreamReader isReader = new InputStreamReader(ins, Charset.defaultCharset());
      BufferedReader bReader = new BufferedReader(isReader);
      StringBuilder webContent = new StringBuilder();
      String str;
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
