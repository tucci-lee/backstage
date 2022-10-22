package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.shiro.Subject;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateBody;
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
public class SysUserUpdateExe {

    private final SysUserService sysUserService;

    public SysUserUpdateExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(SysUserUpdateBody body) {
        // 只有管理员可以修改管理员信息
        if(SysUser.isAdmin(body.getUid())){
            Long uid = Subject.getUid();
            Assert.isTrue(SysUser.isAdmin(uid), BackstageBizCode.PARAMETER_ERROR);
        }

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(body, sysUser);
        sysUserService.update(sysUser);
        return Response.success();
    }
}
