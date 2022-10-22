package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateLockBody;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.service.SysUserService;
import com.tuccicode.concise.dto.Response;
import com.tuccicode.concise.exception.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysUserUpdateLockExe {

    private final SysUserService sysUserService;

    public SysUserUpdateLockExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(SysUserUpdateLockBody body) {
        // 不可以锁定管理员
        Assert.isTrue(!SysUser.isAdmin(body.getUid()), BackstageBizCode.PARAMETER_ERROR);

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(body, sysUser);
        sysUserService.updateLock(sysUser);
        return Response.success();
    }
}
