package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysUserCreateBody;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.service.SysUserService;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysUserCreateExe {

    private final SysUserService sysUserService;

    public SysUserCreateExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(SysUserCreateBody body) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(body, sysUser);
        sysUserService.create(sysUser);
        return Response.success();
    }
}
