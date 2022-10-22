package com.tuccicode.backstage.domain.crontab.convertor;

import com.tuccicode.backstage.domain.crontab.dataobject.CrontabLogDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabLog;

/**
 * @author tucci.lee
 */
public class CrontabLogConvertor {

    public static CrontabLogDO toCreate(CrontabLog entity) {
        return new CrontabLogDO()
                .setCrontabId(entity.getCrontabId())
                .setStartTime(entity.getStartTime())
                .setCreateTime(System.currentTimeMillis());
    }

    public static CrontabLogDO toUpdate(CrontabLog entity) {
        return new CrontabLogDO()
                .setId(entity.getId())
                .setStatus(entity.getStatus())
                .setRunTime(entity.getRunTime())
                .setMessage(entity.getMessage());
    }
}
