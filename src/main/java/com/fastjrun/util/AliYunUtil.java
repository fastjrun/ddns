package com.fastjrun.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse.Domain;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Data;

import java.util.List;

@Data
public class AliYunUtil {
  IAcsClient client = null;

  private String regionId = "cn-hangzhou";
  private String accessKeyId = "";
  private String accessKeySecret = "";

  public void init() {

    IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
    client = new DefaultAcsClient(profile);
  }

  public List<Domain> getDomains() {
    DescribeDomainsRequest request = new DescribeDomainsRequest();
    List<Domain> list = null;
    try {
      DescribeDomainsResponse response = client.getAcsResponse(request);
      list = response.getDomains();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return list;
  }

  public String addDomainRecord(String domainName, String ip, String rR, String type) {
    AddDomainRecordRequest request = new AddDomainRecordRequest();
    request.setDomainName(domainName);
    request.setValue(ip);
    request.setRR(rR);
    request.setTTL(600l);
    request.setLine("default");
    request.setType(type);
    String recordId = null;
    try {
      AddDomainRecordResponse response = client.getAcsResponse(request);
      recordId = response.getRecordId();
    } catch (ServerException e) {
      e.printStackTrace();
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
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return false;
  }
}
