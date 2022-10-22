package com.tuccicode.backstage.domain.system.service;

import com.tuccicode.backstage.domain.system.entity.SysLoginLog;

/**
 * @author tucci.lee
 */
public interface SysLoginLogService {

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     */
    void create(SysLoginLog entity);

}
