package com.tuccicode.backstage.app.system.exe;

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
public class SysUserDeleteExe {

    private final SysUserService sysUserService;

    public SysUserDeleteExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(Long uid) {
        // 不可以删除管理员
        Assert.isTrue(!SysUser.isAdmin(uid), BackstageBizCode.PARAMETER_ERROR);

        sysUserService.delete(uid);
        return Response.success();
    }
}
