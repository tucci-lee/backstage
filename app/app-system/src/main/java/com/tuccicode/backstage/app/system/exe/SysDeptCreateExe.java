package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.dto.body.SysDeptCreateBody;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import com.tuccicode.backstage.domain.system.service.SysDeptService;
import com.tuccicode.concise.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci.lee
 */
@Service
public class SysDeptCreateExe {

    private final SysDeptService sysDeptService;

    public SysDeptCreateExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute(SysDeptCreateBody body) {
        SysDept dept = new SysDept();
        BeanUtils.copyProperties(body, dept);
        sysDeptService.create(dept);
        return Response.success();
    }
}
