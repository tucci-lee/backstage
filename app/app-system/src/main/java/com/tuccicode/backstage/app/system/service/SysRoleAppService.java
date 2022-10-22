package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.dto.body.SysRoleCreateBody;
import com.tuccicode.backstage.app.system.dto.body.SysRoleUpdateBody;
import com.tuccicode.backstage.app.system.dto.body.SysRoleUpdateResBody;
import com.tuccicode.backstage.app.system.exe.SysResListIdByRoleIdExe;
import com.tuccicode.backstage.app.system.exe.SysRoleCreateExe;
import com.tuccicode.backstage.app.system.exe.SysRoleDeleteExe;
import com.tuccicode.backstage.app.system.exe.SysRoleListExe;
import com.tuccicode.backstage.app.system.exe.SysRoleUpdateExe;
import com.tuccicode.backstage.app.system.exe.SysRoleUpdateResExe;
import com.tuccicode.backstage.domain.system.entity.SysRoleQuery;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysRoleAppService {

    private final SysRoleListExe sysRoleListExe;
    private final SysRoleCreateExe sysRoleCreateExe;
    private final SysRoleUpdateExe sysRoleUpdateExe;
    private final SysRoleDeleteExe sysRoleDeleteExe;
    private final SysResListIdByRoleIdExe sysResListIdByRoleIdExe;
    private final SysRoleUpdateResExe sysRoleUpdateResExe;

    public SysRoleAppService(SysRoleListExe sysRoleListExe,
                             SysRoleCreateExe sysRoleCreateExe,
                             SysRoleUpdateExe sysRoleUpdateExe,
                             SysRoleDeleteExe sysRoleDeleteExe,
                             SysResListIdByRoleIdExe sysResListIdByRoleIdExe,
                             SysRoleUpdateResExe sysRoleUpdateResExe) {
        this.sysRoleListExe = sysRoleListExe;
        this.sysRoleCreateExe = sysRoleCreateExe;
        this.sysRoleUpdateExe = sysRoleUpdateExe;
        this.sysRoleDeleteExe = sysRoleDeleteExe;
        this.sysResListIdByRoleIdExe = sysResListIdByRoleIdExe;
        this.sysRoleUpdateResExe = sysRoleUpdateResExe;
    }

    public Response list(SysRoleQuery query) {
        return sysRoleListExe.execute(query);
    }

    public Response create(SysRoleCreateBody body) {
        return sysRoleCreateExe.execute(body);
    }

    public Response update(SysRoleUpdateBody body) {
        return sysRoleUpdateExe.execute(body);
    }

    public Response delete(Long id) {
        return sysRoleDeleteExe.execute(id);
    }

    public Response listResIdById(Long id) {
        return sysResListIdByRoleIdExe.execute(id);
    }

    public Response updateRes(SysRoleUpdateResBody body) {
        return sysRoleUpdateResExe.execute(body);
    }

}
