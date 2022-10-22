package com.tuccicode.backstage.domain.system.service;

import com.tuccicode.backstage.domain.system.entity.SysOperateLog;

/**
 * @author tucci.lee
 */
public interface SysOperateLogService {

    /**
     * 添加操作日志
     *
     * @param entity 操作日志
     */
    void create(SysOperateLog entity);

}
