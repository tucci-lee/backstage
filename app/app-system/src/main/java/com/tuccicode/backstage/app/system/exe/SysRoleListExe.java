package com.tuccicode.backstage.app.system.exe;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.system.assembler.SysRoleAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysRoleVO;
import com.tuccicode.backstage.domain.system.dataobject.SysRoleDO;
import com.tuccicode.backstage.domain.system.entity.SysRoleQuery;
import com.tuccicode.backstage.domain.system.mapper.SysRoleMapper;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysRoleListExe {

    private final SysRoleMapper sysRoleMapper;

    public SysRoleListExe(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    public Response execute(SysRoleQuery query) {
        Page<SysRoleDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        sysRoleMapper.selectPage(page, query);
        List<SysRoleVO> sysRoleVOList = page.getRecords().stream()
                .map(SysRoleAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(sysRoleVOList, (int) page.getTotal());
    }
}
