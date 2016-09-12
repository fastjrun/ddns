package com.fastjrun.ddns.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fastjrun.ddns.service.DomainRecordService;

public class CheckIPTask {    

    protected final Log log = LogFactory.getLog(this.getClass());

    @Value("${checkIPTask.configDomain}")
    String configDomain;
    @Value("${checkIPTask.recordId}")
    String recordId;
    @Value("${checkIPTask.rR}")
    String rR;
    @Autowired
    DomainRecordService domainRecordService;

    public void process() {
        log.debug("checkIP start");
        domainRecordService.updateIPforDomainRecord(configDomain, recordId, rR);
        log.debug("checkIP end");

    }    


    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        CheckIPTask checkIPTask = (CheckIPTask) appContext.getBean("checkIPTask");
        checkIPTask.process();
        ((ClassPathXmlApplicationContext) appContext).close();
    }

}
