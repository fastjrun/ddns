package com.fastjrun.ddns.helper;

import com.fastjrun.ddns.dto.DdnsRecordDTO;
import com.fastjrun.ddns.dto.SysDTO;
import com.fastjrun.ddns.entity.DdnsRecord;
import com.fastjrun.ddns.entity.DdnsSys;

public class DTOToEntityConverter {

    public static DdnsRecord convert(DdnsRecordDTO ddnsRecordDTO) {
        DdnsRecord ddnsRecord = new DdnsRecord();
        ddnsRecord.setRecord(ddnsRecordDTO.getRecord());
        return ddnsRecord;
    }

    public static DdnsSys convert(SysDTO sysDTO) {
        DdnsSys ddnsSys=new DdnsSys();
        ddnsSys.setPropName(sysDTO.getPropName());
        ddnsSys.setPropValue(sysDTO.getPropValue());
        return ddnsSys;
    }
}
