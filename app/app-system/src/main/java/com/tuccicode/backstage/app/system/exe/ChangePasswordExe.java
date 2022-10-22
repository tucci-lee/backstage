package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.shiro.Subject;
import com.tuccicode.backstage.app.system.dto.body.ChangePasswordBody;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.service.SysUserService;
import com.tuccicode.concise.dto.Response;
import com.tuccicode.concise.exception.Assert;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class ChangePasswordExe {

    private final SysUserService sysUserService;

    public ChangePasswordExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(ChangePasswordBody body) {
        Long uid = Subject.getUid();
        SysUser sysUser = sysUserService.getByUid(uid);
        Assert.isTrue(SysUser.verifyPassword(body.getOldPassword(), sysUser.getPassword()), BackstageBizCode.PASSWORD_ERROR);

        SysUser updateUser = new SysUser()
                .setUid(uid)
                .setPassword(body.getPassword());
        sysUserService.updatePassword(updateUser);
        return Response.success();
    }
}
