package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysUserUpdatePasswordBody;
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
public class SysUserUpdatePasswordExe {

    private final SysUserService sysUserService;

    public SysUserUpdatePasswordExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(SysUserUpdatePasswordBody body) {
        // 不可以修改管理员密码
        Assert.isTrue(!SysUser.isAdmin(body.getUid()), BackstageBizCode.PARAMETER_ERROR);

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(body, sysUser);
        sysUserService.updatePassword(sysUser);
        return Response.success();
    }
}
