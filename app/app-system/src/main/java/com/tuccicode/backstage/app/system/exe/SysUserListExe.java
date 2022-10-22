package com.tuccicode.backstage.app.system.exe;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.system.assembler.SysUserAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysUserVO;
import com.tuccicode.backstage.domain.system.dataobject.SysUserDO;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import com.tuccicode.backstage.domain.system.entity.SysUserQuery;
import com.tuccicode.backstage.domain.system.mapper.SysUserMapper;
import com.tuccicode.backstage.domain.system.service.SysDeptService;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysUserListExe {

    private final SysUserMapper sysUserMapper;
    private final SysDeptService sysDeptService;

    public SysUserListExe(SysUserMapper sysUserMapper,
                          SysDeptService sysDeptService) {
        this.sysUserMapper = sysUserMapper;
        this.sysDeptService = sysDeptService;
    }

    public Response execute(SysUserQuery query) {
        Page<SysUserDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        sysUserMapper.selectPage(page, query);

        List<SysUserVO> sysUserVoList = page.getRecords().stream().map(sysUserDO -> {
            String deptName = null;
            if (sysUserDO.getDeptId() != null) {
                SysDept sysDept = sysDeptService.getById(sysUserDO.getDeptId());
                deptName = sysDept == null ? null : sysDept.getName();
            }
            return SysUserAssembler.toVO(sysUserDO, deptName);
        }).collect(Collectors.toList());
        return PageResponse.success(sysUserVoList, (int) page.getTotal());
    }
}
