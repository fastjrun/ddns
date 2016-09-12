package com.fastjrun.ddns.service;

public interface DomainRecordService {

    public void updateIPforDomainRecord(String configDomain, String domainRecordId, String rR);

}
