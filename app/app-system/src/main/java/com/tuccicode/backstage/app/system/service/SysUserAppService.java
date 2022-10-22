package com.tuccicode.backstage.app.system.service;

import com.tuccicode.backstage.app.system.dto.body.SysUserCreateBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateRoleBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateLockBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdatePasswordBody;
import com.tuccicode.backstage.app.system.exe.SysRoleListIdByUidExe;
import com.tuccicode.backstage.app.system.exe.SysUserCreateExe;
import com.tuccicode.backstage.app.system.exe.SysUserDeleteExe;
import com.tuccicode.backstage.app.system.exe.SysUserListExe;
import com.tuccicode.backstage.app.system.exe.SysUserUpdateExe;
import com.tuccicode.backstage.app.system.exe.SysUserUpdateLockExe;
import com.tuccicode.backstage.app.system.exe.SysUserUpdatePasswordExe;
import com.tuccicode.backstage.app.system.exe.SysUserUpdateRoleExe;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.entity.SysUserQuery;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysUserAppService {

    private final SysUserListExe sysUserListExe;
    private final SysUserCreateExe sysUserCreateExe;
    private final SysUserUpdateExe sysUserUpdateExe;
    private final SysUserUpdatePasswordExe sysUserUpdatePasswordExe;
    private final SysUserUpdateLockExe sysUserUpdateLockExe;
    private final SysUserDeleteExe sysUserDeleteExe;
    private final SysRoleListIdByUidExe sysRoleListIdByUidExe;
    private final SysUserUpdateRoleExe sysUserUpdateRoleExe;

    public SysUserAppService(SysUserListExe sysUserListExe,
                             SysUserCreateExe sysUserCreateExe,
                             SysUserUpdateExe sysUserUpdateExe,
                             SysUserUpdatePasswordExe sysUserUpdatePasswordExe,
                             SysUserUpdateLockExe sysUserUpdateLockExe,
                             SysUserDeleteExe sysUserDeleteExe,
                             SysRoleListIdByUidExe sysRoleListIdByUidExe,
                             SysUserUpdateRoleExe sysUserUpdateRoleExe) {
        this.sysUserListExe = sysUserListExe;
        this.sysUserCreateExe = sysUserCreateExe;
        this.sysUserUpdateExe = sysUserUpdateExe;
        this.sysUserUpdatePasswordExe = sysUserUpdatePasswordExe;
        this.sysUserUpdateLockExe = sysUserUpdateLockExe;
        this.sysUserDeleteExe = sysUserDeleteExe;
        this.sysRoleListIdByUidExe = sysRoleListIdByUidExe;
        this.sysUserUpdateRoleExe = sysUserUpdateRoleExe;
    }

    public Response list(SysUserQuery query) {
        return sysUserListExe.execute(query);
    }

    public Response create(SysUserCreateBody body) {
        return sysUserCreateExe.execute(body);
    }

    public Response update(SysUserUpdateBody body) {
        return sysUserUpdateExe.execute(body);
    }

    public Response updatePassword(SysUserUpdatePasswordBody body) {
        return sysUserUpdatePasswordExe.execute(body);
    }

    public Response updateLock(SysUserUpdateLockBody body) {
       return sysUserUpdateLockExe.execute(body);
    }

    public Response delete(Long uid) {
        return sysUserDeleteExe.execute(uid);
    }

    public Response listRoleIdByUid(Long uid) {
        return sysRoleListIdByUidExe.execute(uid);
    }

    public Response updateRole(SysUserUpdateRoleBody body) {
        return sysUserUpdateRoleExe.execute(body);
    }

}
