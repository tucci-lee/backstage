package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.service.SysUserService;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysUserDeleteExe {

    private final SysUserService sysUserService;

    public SysUserDeleteExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(Long uid) {
        sysUserService.delete(uid);
        return Response.success();
    }
}
