package com.tuccicode.backstage.domain.system.service.impl;

import com.tuccicode.backstage.domain.system.convertor.LogLoginConvertor;
import com.tuccicode.backstage.domain.system.dataobject.SysLoginLogDO;
import com.tuccicode.backstage.domain.system.entity.SysLoginLog;
import com.tuccicode.backstage.domain.system.mapper.SysLoginLogMapper;
import com.tuccicode.backstage.domain.system.service.SysLoginLogService;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {
    private final SysLoginLogMapper sysLoginLogMapper;

    public SysLoginLogServiceImpl(SysLoginLogMapper sysLoginLogMapper) {
        this.sysLoginLogMapper = sysLoginLogMapper;
    }

    @Override
    public void create(SysLoginLog entity) {
        SysLoginLogDO create = LogLoginConvertor.toCreate(entity);
        sysLoginLogMapper.insert(create);
    }

}
