package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysResCreateBody;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import com.tuccicode.backstage.domain.system.service.SysResService;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysResCreateExe {

    private final SysResService sysResService;

    public SysResCreateExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(SysResCreateBody body) {
        SysRes res = new SysRes();
        BeanUtils.copyProperties(body, res);
        sysResService.create(res);
        return Response.success();
    }
}
