package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysRoleCreateBody;
import com.tuccicode.backstage.domain.system.entity.SysRole;
import com.tuccicode.backstage.domain.system.service.SysRoleService;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysRoleCreateExe {

    private final SysRoleService sysRoleService;

    public SysRoleCreateExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(SysRoleCreateBody body) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(body, sysRole);
        sysRoleService.create(sysRole);
        return Response.success();
    }
}
