package com.tuccicode.backstage.domain.crontab.service.impl;

import com.tuccicode.backstage.domain.crontab.convertor.CrontabLogConvertor;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabLogDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabLog;
import com.tuccicode.backstage.domain.crontab.mapper.CrontabLogMapper;
import com.tuccicode.backstage.domain.crontab.service.CrontabLogService;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class CrontabLogServiceImpl implements CrontabLogService {

    private final CrontabLogMapper crontabLogMapper;

    public CrontabLogServiceImpl(CrontabLogMapper crontabLogMapper) {
        this.crontabLogMapper = crontabLogMapper;
    }

    @Override
    public Long create(CrontabLog entity) {
        CrontabLogDO create = CrontabLogConvertor.toCreate(entity);
        crontabLogMapper.insert(create);
        return create.getId();
    }

    @Override
    public void update(CrontabLog entity) {
        CrontabLogDO update = CrontabLogConvertor.toUpdate(entity);
        crontabLogMapper.updateById(update);
    }
}
