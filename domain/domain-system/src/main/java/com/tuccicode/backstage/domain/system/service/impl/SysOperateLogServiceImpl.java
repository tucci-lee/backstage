package com.tuccicode.backstage.domain.system.service.impl;

import com.tuccicode.backstage.domain.system.convertor.LogOperateConvertor;
import com.tuccicode.backstage.domain.system.dataobject.SysOperateLogDO;
import com.tuccicode.backstage.domain.system.entity.SysOperateLog;
import com.tuccicode.backstage.domain.system.mapper.SysOperateLogMapper;
import com.tuccicode.backstage.domain.system.service.SysOperateLogService;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysOperateLogServiceImpl implements SysOperateLogService {
    
    private final SysOperateLogMapper sysOperateLogMapper;

    public SysOperateLogServiceImpl(SysOperateLogMapper sysOperateLogMapper) {
        this.sysOperateLogMapper = sysOperateLogMapper;
    }

    @Override
    public void create(SysOperateLog entity) {
        SysOperateLogDO create = LogOperateConvertor.toCreate(entity);
        sysOperateLogMapper.insert(create);
    }
}
