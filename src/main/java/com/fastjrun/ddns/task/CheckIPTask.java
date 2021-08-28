package com.fastjrun.ddns.task;

import com.fastjrun.ddns.config.AppConfig;
import com.fastjrun.ddns.service.DomainRecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckIPTask {

  protected final Log log = LogFactory.getLog(this.getClass());

  @Autowired AppConfig appConfig;

  @Autowired DomainRecordService domainRecordService;

  public void process() {
    log.debug("checkIP start");
    String recordId = domainRecordService.queryDomainRecordId();
    if ("".equals(recordId)) {
      domainRecordService.addDomainRecord();
    } else {
      domainRecordService.updateIPforDomainRecord(recordId);
    }
    log.debug("checkIP end");
  }
}
