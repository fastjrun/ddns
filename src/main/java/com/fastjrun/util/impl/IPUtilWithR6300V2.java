package com.fastjrun.util.impl;

import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fastjrun.util.AbstractIPUtil;
import com.fastjrun.util.IpUtil;


public class IPUtilWithR6300V2 extends AbstractIPUtil implements IpUtil {
    private String adminIP;

    public String getAdminIP() {
        return adminIP;
    }

    public void setAdminIP(String adminIP) {
        this.adminIP = adminIP;
    }

    String login_authorization;

    public String getLogin_authorization() {
        return login_authorization;
    }

    public void setLogin_authorization(String login_authorization) {
        this.login_authorization = login_authorization;
    }

    @Override
    public String locateWanIP() {
        try {
            Response loginResponse = Jsoup
                    .connect(this.adminIP + "/login.cgi")
                    .data("action_mode", "")
                    // 请求参数
                    .data("action_script", "")
                    // 请求参数
                    .data("action_wait", "")
                    // 请求参数
                    .data("current_page", "Main_Login.asp")
                    // 请求参数
                    .data("group_id", "")
                    // 请求参数
                    .data("login_authorization", this.login_authorization)
                    // 请求参数
                    .data("next_page", "index.asp")
                    // 请求参数
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36") // 设置
                                                                                                                                                 // User-Agent

                    .method(Method.POST).execute();
            Map<String, String> cookies = loginResponse.cookies();
            Document doc = Jsoup.connect(this.adminIP + "/index.asp")
                    .cookies(cookies).get();
            String html = doc.html();
            int index = html.indexOf("wanlink_ipaddr() {");
            String htmlTemp = html.substring(
                    index + "wanlink_ipaddr() {".length()).replaceAll("\\s+",
                    "");
            index = htmlTemp.indexOf("}");
            String htmlContainWANIP = htmlTemp.substring(0, index);
            return htmlContainWANIP.split("'")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
