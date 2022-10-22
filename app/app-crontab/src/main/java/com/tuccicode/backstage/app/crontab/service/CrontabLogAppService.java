package com.tuccicode.backstage.app.crontab.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.crontab.assembler.CrontabLogAssembler;
import com.tuccicode.backstage.app.crontab.dto.vo.CrontabLogVO;
import com.tuccicode.backstage.app.crontab.exe.CrontabLogListExe;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabLogDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabLogQuery;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class CrontabLogAppService {

    private final CrontabLogListExe crontabLogListExe;

    public CrontabLogAppService(CrontabLogListExe crontabLogListExe) {
        this.crontabLogListExe = crontabLogListExe;
    }

    public Response list(CrontabLogQuery query) {
        return crontabLogListExe.execute(query);
    }
}
