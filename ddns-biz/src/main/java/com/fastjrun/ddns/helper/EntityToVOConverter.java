package com.fastjrun.ddns.helper;

import com.fastjrun.ddns.entity.DdnsRecord;
import com.fastjrun.ddns.entity.DdnsSys;
import com.fastjrun.ddns.vo.DdnsRecordVO;
import com.fastjrun.ddns.vo.SysVO;

public class EntityToVOConverter {

    public static DdnsRecordVO convert(DdnsRecord ddnsRecord) {
        DdnsRecordVO ddnsRecordVO = new DdnsRecordVO();
        ddnsRecordVO.setRecord(ddnsRecord.getRecord());
        ddnsRecordVO.setId(ddnsRecord.getId());
        ddnsRecordVO.setCreateDate(ddnsRecord.getCreateDate());
        ddnsRecordVO.setUpdateDate(ddnsRecord.getUpdateDate());
        return ddnsRecordVO;
    }


    public static SysVO convert(DdnsSys ddnsSys) {
        SysVO sysVO = new SysVO();
        sysVO.setPropDesc(ddnsSys.getPropDesc());
        sysVO.setPropName(ddnsSys.getPropName());
        sysVO.setPropValue(ddnsSys.getPropValue());
        return sysVO;
    }
}
