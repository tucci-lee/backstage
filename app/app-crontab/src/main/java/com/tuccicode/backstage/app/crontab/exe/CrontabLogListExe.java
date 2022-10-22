package com.tuccicode.backstage.app.crontab.exe;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.crontab.assembler.CrontabLogAssembler;
import com.tuccicode.backstage.app.crontab.dto.vo.CrontabLogVO;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabLogDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabLogQuery;
import com.tuccicode.backstage.domain.crontab.mapper.CrontabLogMapper;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class CrontabLogListExe {

    private final CrontabLogMapper crontabLogMapper;

    public CrontabLogListExe(CrontabLogMapper crontabLogMapper) {
        this.crontabLogMapper = crontabLogMapper;
    }

    public Response execute(CrontabLogQuery query) {
        Page<CrontabLogDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        crontabLogMapper.selectPage(page, query);
        List<CrontabLogVO> crontabLogVOList = page.getRecords().stream()
                .map(CrontabLogAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(crontabLogVOList, (int) page.getTotal());
    }
}
