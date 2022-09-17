package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.dao.DdnsIpChangeLogDao;
import com.fastjrun.ddns.helper.EntityToVOConverter;
import com.fastjrun.ddns.service.IPService;
import com.fastjrun.ddns.vo.DdnsIPVO;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("iPService")
public class IPServiceImpl implements IPService {

  @Resource DdnsIpChangeLogDao ddnsIpChangeLogDao;

  @Override
  public List<DdnsIPVO> list() {
    List<DdnsIPVO> ddnsIPVOS =
        ddnsIpChangeLogDao.select(SelectDSLCompleter.allRows()).stream()
            .map(EntityToVOConverter::convert)
            .collect(Collectors.toList());
    return ddnsIPVOS;
  }

  @Override
  public void deleteById(Long id) {
    ddnsIpChangeLogDao.deleteByPrimaryKey(id);
  }
}
