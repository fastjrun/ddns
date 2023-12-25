package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.config.AliyunConfig;
import com.fastjrun.ddns.config.AppBean;
import com.fastjrun.ddns.config.AppConfig;
import com.fastjrun.ddns.dao.DdnsSysDao;
import com.fastjrun.ddns.entity.DdnsSys;
import com.fastjrun.ddns.service.CacheService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("cacheService")
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Resource
    DdnsSysDao ddnsSysDao;

    AppBean appBean;

    @Override
    public AppBean cache() {
        if (appBean == null) {
            this.refresh();
        }
        return appBean;
    }

    @Override
    public void refresh() {
        List<DdnsSys> ddnsSysList = ddnsSysDao.select(SelectDSLCompleter.allRows());

        Map<String, String> ddnsSysMap = Maps.newHashMap();
        ddnsSysList.stream().forEach(
                var -> {
                    ddnsSysMap.put(var.getPropName(), var.getPropValue());
                }
        );
        this.appBean = new AppBean();
        this.appBean.setLocateIPUrl(ddnsSysMap.get(AppConfig.LOCATEIP_URL));
        this.appBean.setConfigDomain(ddnsSysMap.get(AppConfig.CONFIG_DOMAIN));
        this.appBean.setInterval(Integer.parseInt(ddnsSysMap.get(AppConfig.INTERVAL)));
        this.appBean.setType(ddnsSysMap.get(AppConfig.TYPE));
        AliyunConfig aliyunConfig = new AliyunConfig();
        aliyunConfig.setAccessKeyId(ddnsSysMap.get(AppConfig.ACCESSKEY_ID));
        aliyunConfig.setAccessKeySecret(ddnsSysMap.get(AppConfig.ACCESSKEY_SECRET));
        this.appBean.setAliyunConfig(aliyunConfig);

    }
}
