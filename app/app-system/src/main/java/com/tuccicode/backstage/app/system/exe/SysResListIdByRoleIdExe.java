package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.entity.SysRes;
import com.tuccicode.backstage.domain.system.service.SysResService;
import com.tuccicode.concise.dto.Response;
import com.tuccicode.concise.dto.SingletonResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysResListIdByRoleIdExe {

    private final SysResService sysResService;

    public SysResListIdByRoleIdExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(Long roleId){
        List<Long> resIds = sysResService.listByRoleId(roleId)
                .stream()
                .map(SysRes::getId)
                .collect(Collectors.toList());
        return SingletonResponse.success(resIds);
    }
}
