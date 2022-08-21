package com.fastjrun.ddns.service.impl;

import com.fastjrun.ddns.dao.DdnsRecordDao;
import com.fastjrun.ddns.dto.DdnsRecordDTO;
import com.fastjrun.ddns.entity.DdnsRecord;
import com.fastjrun.ddns.helper.DTOToEntityConverter;
import com.fastjrun.ddns.helper.EntityToVOConverter;
import com.fastjrun.ddns.service.RecordService;
import com.fastjrun.ddns.vo.DdnsRecordVO;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("recordService")
public class RecordServiceImpl implements RecordService {
    @Resource
    DdnsRecordDao ddnsRecordDao;

    @Override
    public List<DdnsRecordVO> list() {
        List<DdnsRecordVO> ddnsRecordVOS = ddnsRecordDao.select(SelectDSLCompleter.allRows()).stream().map(EntityToVOConverter::convert
        ).collect(Collectors.toList());
        return ddnsRecordVOS;
    }

    @Override
    public void add(DdnsRecordDTO ddnsRecordDTO) {
        DdnsRecord ddnsRecord = DTOToEntityConverter.convert(ddnsRecordDTO);
        Date date=new Date();
        ddnsRecord.setCreateDate(date);
        ddnsRecord.setUpdateDate(date);
        ddnsRecordDao.insert(ddnsRecord);
    }

    @Override
    public void deleteById(Long id) {
        ddnsRecordDao.deleteByPrimaryKey(id);

    }
}
