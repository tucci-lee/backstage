package com.tuccicode.backstage.app.system.controller;

import com.tuccicode.backstage.app.aspect.Operate;
import com.tuccicode.backstage.app.system.dto.body.SysUserCreateBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateLockBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdatePasswordBody;
import com.tuccicode.backstage.app.system.dto.body.SysUserUpdateRoleBody;
import com.tuccicode.backstage.app.system.service.SysUserAppService;
import com.tuccicode.backstage.domain.system.entity.SysUserQuery;
import com.tuccicode.concise.dto.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci.lee
 */
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    private final SysUserAppService sysUserAppService;

    public SysUserController(SysUserAppService sysUserAppService) {
        this.sysUserAppService = sysUserAppService;
    }

    /**
     * 用户列表查询
     */
    @RequiresPermissions(value = {"sys:user:list"})
    @GetMapping
    public Response list(SysUserQuery query) {
        return sysUserAppService.list(query);
    }

    /**
     * 添加用户
     */
    @Operate("添加用户")
    @RequiresPermissions(value = {"sys:user:create"})
    @PostMapping
    public Response create(@Validated @RequestBody SysUserCreateBody body) {
        return sysUserAppService.create(body);
    }

    /**
     * 修改用户
     */
    @Operate("修改用户")
    @RequiresPermissions(value = {"sys:user:update"})
    @PutMapping
    public Response update(@Validated @RequestBody SysUserUpdateBody body) {
        return sysUserAppService.update(body);
    }

    /**
     * 修改密码
     */
    @Operate("修改用户密码")
    @RequiresPermissions(value = {"sys:user:update:password"})
    @PutMapping("password")
    public Response updatePassword(@Validated @RequestBody SysUserUpdatePasswordBody body) {
        return sysUserAppService.updatePassword(body);
    }

    /**
     * 修改锁定状态
     */
    @Operate("修改用户锁定状态")
    @RequiresPermissions(value = {"sys:user:update:lock"})
    @PutMapping("lock")
    public Response updateLock(@Validated @RequestBody SysUserUpdateLockBody body) {
        return sysUserAppService.updateLock(body);
    }

    /**
     * 删除用户
     */
    @Operate("删除用户")
    @RequiresPermissions(value = {"sys:user:delete"})
    @DeleteMapping("{uid}")
    public Response delete(@PathVariable Long uid) {
        return sysUserAppService.delete(uid);
    }


    /**
     * 查询用户关联的角色id
     */
    @RequiresPermissions(value = {"sys:user:list"})
    @GetMapping("role/{uid}")
    public Response listRole(@PathVariable Long uid) {
        return sysUserAppService.listRoleIdByUid(uid);
    }

    /**
     * 修改用户关联的角色
     */
    @Operate("修改用户关联的角色")
    @RequiresPermissions(value = {"sys:user:update"})
    @PutMapping("role")
    public Response updateRole(@Validated @RequestBody SysUserUpdateRoleBody body) {
        return sysUserAppService.updateRole(body);
    }
}
