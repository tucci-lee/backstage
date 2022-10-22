package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.domain.system.entity.SysRole;
import com.tuccicode.backstage.domain.system.service.SysRoleService;
import com.tuccicode.concise.dto.Response;
import com.tuccicode.concise.dto.SingletonResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysRoleListIdByUidExe {

    private final SysRoleService sysRoleService;

    public SysRoleListIdByUidExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(Long uid){
        List<Long> roleIds = sysRoleService.listByUid(uid)
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
        return SingletonResponse.success(roleIds);
    }
}
