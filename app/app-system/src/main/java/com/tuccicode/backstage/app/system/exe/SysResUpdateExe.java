package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysResUpdateBody;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import com.tuccicode.backstage.domain.system.service.SysResService;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysResUpdateExe {

    private final SysResService sysResService;

    public SysResUpdateExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(SysResUpdateBody body) {
        SysRes res = new SysRes();
        BeanUtils.copyProperties(body, res);
        sysResService.update(res);
        return Response.success();
    }
}
