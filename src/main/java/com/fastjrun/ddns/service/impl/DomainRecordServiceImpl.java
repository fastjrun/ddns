package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.service.DomainRecordService;
import com.fastjrun.util.AliYunUtil;
import com.fastjrun.util.IpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("domainRecordService")
public class DomainRecordServiceImpl implements DomainRecordService {
  protected final Log log = LogFactory.getLog(this.getClass());

  @Autowired IpUtil ipUtil;

  @Autowired AliYunUtil aliYunUtil;

  @Value("#{appConfig.checkIPTask.configDomain}")
  String configDomain;

  @Value("#{appConfig.checkIPTask.RR}")
  String rR;

  @Override
  public String addDomainRecord() {
    String ipWan = ipUtil.locateWanIP();
    log.debug("ipWan=" + ipWan);
    return aliYunUtil.addDomainRecord(configDomain, ipWan, rR, "A");
  }

  @Override
  public void updateIPforDomainRecord(String domainRecordId) {
    String ipDomain = ipUtil.getIPByDomain(rR + "." + configDomain);
    log.debug("ipDomain=" + ipDomain);

    String ipWan = ipUtil.locateWanIP();
    log.debug("ipWan=" + ipWan);

    if (!ipWan.equals(ipDomain)) {
      aliYunUtil.updateATypeDomainRecord(domainRecordId, ipWan, rR);
    }
  }

  @Override
  public String queryDomainRecordId() {
    log.debug("queryDomainRecordId");

    return aliYunUtil.queryATypeDomainRecordId(configDomain, rR);
  }
}
