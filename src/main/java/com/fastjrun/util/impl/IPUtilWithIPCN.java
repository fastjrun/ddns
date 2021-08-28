package com.fastjrun.util.impl;

import com.fastjrun.util.AbstractIPUtil;
import com.fastjrun.util.IpUtil;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IPUtilWithIPCN extends AbstractIPUtil implements IpUtil {

  private static final int DEFAULT_CONNECT_TIMEOUT = 5000;
  private static final int DEFAULT_READ_TIMEOUT = 30000;
  private static final int DEFAULT_WRITE_TIMEOUT = 10000;
  private static final int DEFAULT_KEEPALIVE_TIME = 5 * 60 * 1000;

  private int maxTotal = 100;
  private boolean redirectable = true;

  private OkHttpClient okHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    return builder
        .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectionPool(new ConnectionPool(maxTotal, DEFAULT_KEEPALIVE_TIME, TimeUnit.MILLISECONDS))
        .followRedirects(redirectable)
        .build();
  }

  private static String user_agent_value =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36";

  @Override
  public String locateWanIP() {
    InputStream ins = null;
    OkHttpClient httpClient = okHttpClient();
    Headers.Builder headBuilder = new Headers.Builder();
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder.url("https://ip.cn/api/index?ip=&type=0").headers(headBuilder.build());
    Request request = requestBuilder.build();
    try (Response response = httpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        String result = response.body().string();

        Map<String, String> map = new HashMap<>(10);
        Arrays.stream(result.replaceAll("\"", "").split(","))
            .forEach(
                var -> {
                  String[] keyValue = var.split(":");
                  map.put(keyValue[0], keyValue[1]);
                });
        return map.get("ip");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
