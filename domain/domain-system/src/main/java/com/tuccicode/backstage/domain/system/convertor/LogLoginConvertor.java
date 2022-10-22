package com.tuccicode.backstage.domain.system.convertor;

import com.tuccicode.backstage.domain.system.dataobject.SysLoginLogDO;
import com.tuccicode.backstage.domain.system.entity.SysLoginLog;

/**
 * @author tucci.lee
 */
public class LogLoginConvertor {

    public static SysLoginLogDO toCreate(SysLoginLog entity) {
        return new SysLoginLogDO()
                .setUsername(entity.getUsername())
                .setOs(entity.getOs())
                .setBrowser(entity.getBrowser())
                .setIp(entity.getIp())
                .setStatus(entity.getStatus())
                .setMessage(entity.getMessage())
                .setCreateTime(System.currentTimeMillis());
    }
}
