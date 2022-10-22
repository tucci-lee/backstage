package com.tuccicode.backstage.app.system.exe;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.system.assembler.LogLoginAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysLoginLogVO;
import com.tuccicode.backstage.domain.system.dataobject.SysLoginLogDO;
import com.tuccicode.backstage.domain.system.entity.SysLoginLogQuery;
import com.tuccicode.backstage.domain.system.mapper.SysLoginLogMapper;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysLoginLogListExe {

    private final SysLoginLogMapper sysLoginLogMapper;

    public SysLoginLogListExe(SysLoginLogMapper sysLoginLogMapper) {
        this.sysLoginLogMapper = sysLoginLogMapper;
    }

    public Response execute(SysLoginLogQuery query) {
        Page<SysLoginLogDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        sysLoginLogMapper.selectPage(page, query);
        List<SysLoginLogVO> sysLoginLogVOList = page.getRecords().stream()
                .map(LogLoginAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(sysLoginLogVOList, (int) page.getTotal());
    }
}
