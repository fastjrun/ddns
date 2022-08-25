package com.fastjrun.ddns.service.impl;

import com.fastjrun.client.AliYunClient;
import com.fastjrun.client.IpClient;
import com.fastjrun.client.common.AliyunRecord;
import com.fastjrun.client.impl.IPClientWithNetService;
import com.fastjrun.ddns.config.AppBean;
import com.fastjrun.ddns.dao.DdnsRecordDao;
import com.fastjrun.ddns.entity.DdnsRecord;
import com.fastjrun.ddns.service.CacheService;
import com.fastjrun.ddns.service.DomainRecordService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("domainRecordService")
@Slf4j
public class DomainRecordServiceImpl implements DomainRecordService {
    @Resource
    DdnsRecordDao ddnsRecordDao;

    @Resource
    CacheService cacheService;

    @Override
    public boolean updateIPforDomainRecord() {
        AppBean appBean = cacheService.cache();
        IpClient ipClient = this.ipClient(appBean);

        String ipWan = ipClient.locateWanIP();
        log.debug("ipWan={}", ipWan);

        AliYunClient aliYunClient = this.aliYunClient(appBean);
        List<DdnsRecord> ddnsRecordList = ddnsRecordDao.select(SelectDSLCompleter.allRows());
        ddnsRecordList.stream().forEach(var -> {
            String ipDomain = ipClient.getIPByDomain(var.getRecord() + "." + appBean.getConfigDomain());
            log.debug("ddns={},ipDomain={}", var.getRecord() + "." + appBean.getConfigDomain(), ipDomain);
            if (!ipWan.equals(ipDomain)) {
                AliyunRecord aliyunRecord = aliYunClient.queryATypeDomainRecordId(appBean.getConfigDomain(), var.getRecord());
                String recordId = aliyunRecord.getRecordId();
                String value = aliyunRecord.getValue();
                log.debug("recordId={},value={}", recordId, value);
                if (value == null) {
                    recordId = aliYunClient.addDomainRecord(appBean.getConfigDomain(), ipWan, var.getRecord(), "A");
                    log.debug("add record ok for recordId:{}", recordId);
                } else {
                    if (value.equals(ipWan)) {
                        log.debug("no need to change");
                    } else {
                        aliYunClient.updateATypeDomainRecord(recordId, ipWan, var.getRecord());
                        log.debug("update record ok for recordId:{}", recordId);
                    }

                }
            }
        });

        return true;
    }


    private IpClient ipClient(AppBean appBean) {
        IPClientWithNetService ipClient = new IPClientWithNetService();
        ipClient.setLocateIPUrl(appBean.getLocateIPUrl());
        return ipClient;
    }

    private AliYunClient aliYunClient(AppBean appBean) {
        AliYunClient aliYunClient = new AliYunClient();
        aliYunClient.setRegionId(appBean.getAliyunConfig().getRegionId());
        aliYunClient.setAccessKeyId(appBean.getAliyunConfig().getAccessKeyId());
        aliYunClient.setAccessKeySecret(appBean.getAliyunConfig().getAccessKeySecret());
        aliYunClient.init();
        return aliYunClient;
    }
}
