package com.fastjrun.ddns.service;

public interface DomainRecordService {

  public String addDomainRecord();

  public void updateIPforDomainRecord(String domainRecordId);

  public String queryDomainRecordId();
}
