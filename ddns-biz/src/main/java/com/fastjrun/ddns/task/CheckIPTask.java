package com.fastjrun.ddns.task;

import com.fastjrun.ddns.service.DomainRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("checkIPTask")
@Slf4j
public class CheckIPTask {

  @Resource DomainRecordService domainRecordService;

  public void process() {
    log.debug("checkIP start");
    domainRecordService.updateIPforDomainRecord();
    log.debug("checkIP end");
  }
}
