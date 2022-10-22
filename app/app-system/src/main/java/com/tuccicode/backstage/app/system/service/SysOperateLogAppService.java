package com.tuccicode.backstage.app.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.system.assembler.LogOperateAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysOperateLogVO;
import com.tuccicode.backstage.app.system.exe.SysOperateLogListExe;
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
public class SysOperateLogAppService {

    private final SysOperateLogListExe sysOperateLogListExe;

    public SysOperateLogAppService( SysOperateLogListExe sysOperateLogListExe) {
        this.sysOperateLogListExe = sysOperateLogListExe;
    }

    public Response list(SysOperateLogQuery query) {
        return sysOperateLogListExe.execute(query);
    }
}
