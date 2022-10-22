package com.tuccicode.backstage.app.system.controller;

import com.tuccicode.backstage.app.system.service.SysLoginLogAppService;
import com.tuccicode.backstage.domain.system.entity.SysLoginLogQuery;
import com.tuccicode.concise.dto.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci.lee
 */
@RestController
@RequestMapping("log/login")
public class SysLoginLogController {

    private final SysLoginLogAppService sysLoginLogAppService;

    public SysLoginLogController(SysLoginLogAppService sysLoginLogAppService) {
        this.sysLoginLogAppService = sysLoginLogAppService;
    }

    /**
     * 查询登录日志列表
     */
    @RequiresPermissions(value = {"log:login:list"})
    @GetMapping
    public Response list(SysLoginLogQuery query){
        return sysLoginLogAppService.list(query);
    }
}
