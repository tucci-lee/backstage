package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.service.SysDeptService;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysDeptDeleteExe {

    private final SysDeptService sysDeptService;

    public SysDeptDeleteExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute(Long id) {
        sysDeptService.delete(id);
        return Response.success();
    }
}
