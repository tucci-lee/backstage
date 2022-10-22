package com.tuccicode.backstage.app.system.exe;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.system.assembler.LogOperateAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysOperateLogVO;
import com.tuccicode.backstage.domain.system.dataobject.SysOperateLogDO;
import com.tuccicode.backstage.domain.system.entity.SysOperateLogQuery;
import com.tuccicode.backstage.domain.system.mapper.SysOperateLogMapper;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysOperateLogListExe {

    private final SysOperateLogMapper sysOperateLogMapper;

    public SysOperateLogListExe(SysOperateLogMapper sysOperateLogMapper) {
        this.sysOperateLogMapper = sysOperateLogMapper;
    }

    public Response execute(SysOperateLogQuery query) {
        Page<SysOperateLogDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        sysOperateLogMapper.selectPage(page, query);
        List<SysOperateLogVO> sysOperateLogVOList = page.getRecords().stream()
                .map(LogOperateAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(sysOperateLogVOList, (int) page.getTotal());
    }
}
