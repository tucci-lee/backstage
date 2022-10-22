package com.tuccicode.backstage.domain.crontab.service;

import com.tuccicode.backstage.domain.crontab.entity.CrontabLog;

/**
 * @author tucci.lee
 */
public interface CrontabLogService {

    /**
     * 添加定时任务日志
     *
     * @param entity 日志
     * @return 日志id
     */
    Long create(CrontabLog entity);

    /**
     * 修改定时任务日志
     *
     * @param entity entity
     */
    void update(CrontabLog entity);
}
