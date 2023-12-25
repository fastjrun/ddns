package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.dao.DdnsSysDao;
import com.fastjrun.ddns.dto.SysDTO;
import com.fastjrun.ddns.entity.DdnsSys;
import com.fastjrun.ddns.helper.DTOToEntityConverter;
import com.fastjrun.ddns.helper.EntityToVOConverter;
import com.fastjrun.ddns.service.CacheService;
import com.fastjrun.ddns.service.SysService;
import com.fastjrun.ddns.vo.SysVO;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("sysService")
public class SysServiceImpl implements SysService {
    @Resource
    DdnsSysDao ddnsSysDao;
    @Resource
    CacheService cacheService;
    @Override
    public void save(SysDTO sysDTO) {
        DdnsSys ddnsSys = DTOToEntityConverter.convert(sysDTO);
        ddnsSysDao.updateByPrimaryKeySelective(ddnsSys);
        cacheService.refresh();
    }

    @Override
    public List<SysVO> list() {
        List<SysVO> sysVOS = ddnsSysDao.select(SelectDSLCompleter.allRows()).stream().map(EntityToVOConverter::convert).collect(Collectors.toList());
        return sysVOS;
    }
}
