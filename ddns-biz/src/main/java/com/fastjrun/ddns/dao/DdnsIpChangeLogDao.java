package com.fastjrun.ddns.dao;

import com.fastjrun.ddns.mapper.DdnsIpChangeLogMapper;
import org.apache.ibatis.annotations.DeleteProvider;

public interface DdnsIpChangeLogDao extends DdnsIpChangeLogMapper {

    @DeleteProvider(type=DdnsIpChangeLogSqlProviderAdapter.class, method="clearLastByLimit")
    int clearLastByLimit(Integer limit);


}
