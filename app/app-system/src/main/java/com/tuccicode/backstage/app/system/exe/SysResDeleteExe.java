package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.service.SysResService;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysResDeleteExe {

    private final SysResService sysResService;

    public SysResDeleteExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(Long id) {
        sysResService.delete(id);
        return Response.success();
    }
}
