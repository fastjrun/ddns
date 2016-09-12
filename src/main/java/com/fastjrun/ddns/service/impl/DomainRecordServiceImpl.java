package com.fastjrun.ddns.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastjrun.ddns.service.DomainRecordService;
import com.fastjrun.util.AliYunUtil;
import com.fastjrun.util.IpUtil;



@Service("domainRecordService")
public class DomainRecordServiceImpl implements DomainRecordService {
    protected final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    IpUtil ipUtil;

    @Autowired
    AliYunUtil aliYunUtil;

    @Override
    public void updateIPforDomainRecord(String configDomain, String domainRecordId, String rR) {
        String ipDomain = ipUtil.getIPByDomain(rR + "." + configDomain);
        log.debug("ipDomain=" + ipDomain);

        String ipWan = ipUtil.locateWanIP();
        log.debug("ipWan=" + ipWan);

        if (!ipWan.equals(ipDomain)) {
            aliYunUtil.updateATypeDomainRecord(domainRecordId, ipWan, rR);
        }

    }

}
