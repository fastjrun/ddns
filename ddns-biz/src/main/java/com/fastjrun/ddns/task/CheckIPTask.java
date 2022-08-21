package com.fastjrun.ddns.task;

import com.fastjrun.ddns.config.AppBean;
import com.fastjrun.ddns.service.DomainRecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("checkIPTask")
public class CheckIPTask {

    protected final Log log = LogFactory.getLog(this.getClass());

    @Resource
    DomainRecordService domainRecordService;

    public void process() {
        log.debug("checkIP start");
        domainRecordService.updateIPforDomainRecord();
        log.debug("checkIP end");
    }
}
