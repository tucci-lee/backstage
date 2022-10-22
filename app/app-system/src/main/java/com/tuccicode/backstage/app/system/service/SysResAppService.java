package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.dto.body.SysResCreateBody;
import com.tuccicode.backstage.app.system.dto.body.SysResUpdateBody;
import com.tuccicode.backstage.app.system.exe.SysResCreateExe;
import com.tuccicode.backstage.app.system.exe.SysResDeleteExe;
import com.tuccicode.backstage.app.system.exe.SysResOwnedMenusExe;
import com.tuccicode.backstage.app.system.exe.SysResTreeExe;
import com.tuccicode.backstage.app.system.exe.SysResUpdateExe;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysResAppService {

    private final SysResOwnedMenusExe sysResOwnedMenusExe;
    private final SysResTreeExe sysResTreeExe;
    private final SysResCreateExe sysResCreateExe;
    private final SysResDeleteExe sysResDeleteExe;
    private final SysResUpdateExe sysResUpdateExe;

    public SysResAppService(SysResOwnedMenusExe sysResOwnedMenusExe,
                            SysResTreeExe sysResTreeExe,
                            SysResCreateExe sysResCreateExe,
                            SysResDeleteExe sysResDeleteExe,
                            SysResUpdateExe sysResUpdateExe) {
        this.sysResOwnedMenusExe = sysResOwnedMenusExe;
        this.sysResTreeExe = sysResTreeExe;
        this.sysResCreateExe = sysResCreateExe;
        this.sysResDeleteExe = sysResDeleteExe;
        this.sysResUpdateExe = sysResUpdateExe;
    }

    public Response ownedMenus() {
        return sysResOwnedMenusExe.execute();
    }

    public Response tree() {
        return sysResTreeExe.execute();
    }

    public Response create(SysResCreateBody body) {
        return sysResCreateExe.execute(body);
    }

    public Response delete(Long id) {
        return sysResDeleteExe.execute(id);
    }

    public Response update(SysResUpdateBody body) {
        return sysResUpdateExe.execute(body);
    }
}
