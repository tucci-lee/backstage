package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysDeptUpdateBody;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import com.tuccicode.backstage.domain.system.service.SysDeptService;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysDeptUpdateExe {

    private final SysDeptService sysDeptService;

    public SysDeptUpdateExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute(SysDeptUpdateBody body) {
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(body, dept);
        sysDeptService.update(dept);
        return Response.success();
    }
}
