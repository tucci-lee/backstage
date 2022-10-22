package com.tuccicode.backstage.app.crontab.controller;

import com.tuccicode.backstage.app.crontab.service.CrontabLogAppService;
import com.tuccicode.backstage.domain.crontab.entity.CrontabLogQuery;
import com.tuccicode.concise.dto.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci.lee
 */
@RestController
@RequestMapping("crontab/log")
public class CrontabLogController {

    private final CrontabLogAppService crontabLogAppService;

    public CrontabLogController(CrontabLogAppService crontabLogAppService) {
        this.crontabLogAppService = crontabLogAppService;
    }

    @RequiresPermissions(value = {"crontab:list"})
    @GetMapping
    public Response list(@Validated CrontabLogQuery query){
        return crontabLogAppService.list(query);
    }
}
