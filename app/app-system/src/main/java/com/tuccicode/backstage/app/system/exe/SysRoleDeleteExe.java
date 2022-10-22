package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.service.SysRoleService;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysRoleDeleteExe {

    private final SysRoleService sysRoleService;

    public SysRoleDeleteExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(Long id) {
        sysRoleService.delete(id);
        return Response.success();
    }
}
