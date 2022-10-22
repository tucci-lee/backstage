package com.tuccicode.backstage.domain.system.convertor;

import com.tuccicode.backstage.domain.system.dataobject.SysOperateLogDO;
import com.tuccicode.backstage.domain.system.entity.SysOperateLog;

/**
 * @author tucci.lee
 */
public class LogOperateConvertor {

    public static SysOperateLogDO toCreate(SysOperateLog entity) {
        return new SysOperateLogDO()
                .setUsername(entity.getUsername())
                .setIp(entity.getIp())
                .setUrl(entity.getUrl())
                .setMethod(entity.getMethod())
                .setParams(entity.getParams())
                .setResult(entity.getResult())
                .setDescription(entity.getDescription())
                .setErrorMessage(entity.getErrorMessage())
                .setStatus(entity.getStatus())
                .setCreateTime(System.currentTimeMillis());
    }
}
