package com.fastjrun.ddns.task;

import com.fastjrun.ddns.dao.DdnsIpChangeLogDao;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("clearIPChangeLogTask")
@Slf4j
public class ClearIPChangeLogTask {

  static final int retailCount = 50;

  @Resource DdnsIpChangeLogDao ddnsIpChangeLogDao;

  public void process() {
    log.debug("clear ip change log task start");
    long count = ddnsIpChangeLogDao.count(CountDSLCompleter.allRows());
    if (count > retailCount) {
      //ddnsIpChangeLogDao.clearLastByLimit((int) count - retailCount);
      ddnsIpChangeLogDao.delete(c->c.where());
    }
    log.debug("clear ip change log task end");
  }
}
