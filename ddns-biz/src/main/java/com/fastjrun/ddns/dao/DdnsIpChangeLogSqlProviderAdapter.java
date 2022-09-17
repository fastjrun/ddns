package com.fastjrun.ddns.dao;

import com.fastjrun.ddns.mapper.DdnsIpChangeLogDynamicSqlSupport;
import org.apache.ibatis.jdbc.SQL;

public class DdnsIpChangeLogSqlProviderAdapter {

  public String clearLastByLimit(Integer limit) {
    return new SQL() {
      {
        DELETE_FROM(DdnsIpChangeLogDynamicSqlSupport.ddnsIpChangeLog.tableNameAtRuntime());
        LIMIT(limit);
      }
    }.toString();
  }
}
