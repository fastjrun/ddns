package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.service.DomainRecordService;
import com.fastjrun.util.AliYunUtil;
import com.fastjrun.util.IpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("domainRecordService")
public class DomainRecordServiceImpl implements DomainRecordService {
  protected final Log log = LogFactory.getLog(this.getClass());

  @Autowired IpUtil ipUtil;

  @Autowired AliYunUtil aliYunUtil;

  @Override
  public boolean updateIPforDomainRecord(String configDomain, String rR) {
    String ipDomain = ipUtil.getIPByDomain(rR + "." + configDomain);
    log.debug("ipDomain=" + ipDomain);

    String ipWan = ipUtil.locateWanIP();
    log.debug("ipWan=" + ipWan);

    if (!ipWan.equals(ipDomain)) {
      String recordId = aliYunUtil.queryATypeDomainRecordId(configDomain, rR);
      log.debug("recordId=" + recordId);

      if ("".equals(recordId)) {
        aliYunUtil.addDomainRecord(configDomain, ipWan, rR, "A");
      } else {
        aliYunUtil.updateATypeDomainRecord(recordId, ipWan, rR);
      }
    }
    return true;
  }
}
