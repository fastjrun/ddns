package com.fastjrun.ddns.config;

import com.fastjrun.ddns.dao.DdnsSysDao;
import com.fastjrun.ddns.entity.DdnsSys;
import com.google.common.collect.Maps;
import lombok.Data;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Data
public class AppBean {

    private String locateIPUrl;
    private String configDomain;
    private Integer interval;

    private AliyunConfig aliyunConfig;

}
