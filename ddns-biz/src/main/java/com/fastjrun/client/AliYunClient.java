package com.fastjrun.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fastjrun.client.common.AliyunRecord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AliYunClient {
  IAcsClient client = null;

  private String regionId = "cn-hangzhou";
  private String accessKeyId = "";
  private String accessKeySecret = "";

  public void init() {

    IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
    client = new DefaultAcsClient(profile);
  }

  public String addDomainRecord(String domainName, String ip, String rR, String type) {
    AddDomainRecordRequest request = new AddDomainRecordRequest();
    request.setDomainName(domainName);
    request.setValue(ip);
    request.setRR(rR);
    request.setTTL(600L);
    request.setLine("default");
    request.setType(type);
    String recordId = "";
    try {
      AddDomainRecordResponse response = client.getAcsResponse(request);
      recordId = response.getRecordId();
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return recordId;
  }

  public boolean updateATypeDomainRecord(String recordId, String ip, String rR) {
    return this.updateDomainRecord(recordId, ip, rR, "A");
  }

  public boolean updateDomainRecord(String recordId, String ip, String rR, String type) {
    UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
    request.setRecordId(recordId);
    request.setValue(ip);
    request.setRR(rR);
    request.setTTL(600L);
    request.setLine("default");
    request.setType(type);
    try {
      UpdateDomainRecordResponse response = client.getAcsResponse(request);
      String recordIdRes = response.getRecordId();
      if (recordId.equals(recordIdRes)) {
        return true;
      }
    } catch (ClientException e) {
      log.error("",e);
    }
    return false;
  }

  public AliyunRecord queryATypeDomainRecordId(String domainName, String rR) {
    AliyunRecord aliyunRecord=new AliyunRecord();
    aliyunRecord.setDomainName(domainName);
    aliyunRecord.setRR(rR);
    DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest();
    describeDomainRecordsRequest.setDomainName(domainName);
    describeDomainRecordsRequest.setRRKeyWord(rR);
    try {
      DescribeDomainRecordsResponse describeDomainRecordsResponse =
          client.getAcsResponse(describeDomainRecordsRequest);
      if (describeDomainRecordsResponse.getDomainRecords() != null
          && describeDomainRecordsResponse.getDomainRecords().size() > 0) {
        aliyunRecord.setRecordId(describeDomainRecordsResponse.getDomainRecords().get(0).getRecordId());
        aliyunRecord.setValue(describeDomainRecordsResponse.getDomainRecords().get(0).getValue());
      }
    } catch (ClientException e) {
      log.error("",e);
    }
    return aliyunRecord;
  }
}
