package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.exe.SysLoginLogListExe;
import com.tuccicode.backstage.domain.system.entity.SysLoginLogQuery;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysLoginLogAppService {

    private final SysLoginLogListExe sysLoginLogListExe;

    public SysLoginLogAppService(SysLoginLogListExe sysLoginLogListExe) {
        this.sysLoginLogListExe = sysLoginLogListExe;
    }

    public Response list(SysLoginLogQuery query) {
        return sysLoginLogListExe.execute(query);
    }
}
