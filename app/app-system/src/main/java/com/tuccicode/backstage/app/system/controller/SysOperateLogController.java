package com.tuccicode.backstage.app.system.controller;

import com.tuccicode.backstage.app.system.service.SysOperateLogAppService;
import com.tuccicode.backstage.domain.system.entity.SysOperateLogQuery;
import com.tuccicode.concise.dto.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci.lee
 */
@RestController
@RequestMapping("log/operate")
public class SysOperateLogController {

    private final SysOperateLogAppService sysOperateLogAppService;

    public SysOperateLogController(SysOperateLogAppService sysOperateLogAppService) {
        this.sysOperateLogAppService = sysOperateLogAppService;
    }

    /**
     * 查询操作日志列表
     */
    @RequiresPermissions(value = {"log:operate:list"})
    @GetMapping
    public Response list(SysOperateLogQuery query){
        return sysOperateLogAppService.list(query);
    }
}
