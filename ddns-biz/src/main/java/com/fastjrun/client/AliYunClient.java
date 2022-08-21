package com.fastjrun.client;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Data;

@Data
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
      e.printStackTrace();
    }
    return false;
  }

  public String queryATypeDomainRecordId(String domainName, String rR) {
    DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest();
    describeDomainRecordsRequest.setDomainName(domainName);
    describeDomainRecordsRequest.setRRKeyWord(rR);
    String recordId = "";
    try {
      DescribeDomainRecordsResponse describeDomainRecordsResponse =
          client.getAcsResponse(describeDomainRecordsRequest);
      if (describeDomainRecordsResponse.getDomainRecords() != null
          && describeDomainRecordsResponse.getDomainRecords().size() > 0) {
        recordId = describeDomainRecordsResponse.getDomainRecords().get(0).getRecordId();
      }
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return recordId;
  }
}
