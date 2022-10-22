package com.tuccicode.backstage.app.crontab.exe;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.app.crontab.assembler.CrontabAssembler;
import com.tuccicode.backstage.app.crontab.dto.vo.CrontabVO;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabQuery;
import com.tuccicode.backstage.domain.crontab.mapper.CrontabMapper;
import com.tuccicode.concise.dto.PageResponse;
import com.tuccicode.concise.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class CrontabListExe {

    private final CrontabMapper crontabMapper;

    public CrontabListExe(CrontabMapper crontabMapper) {
        this.crontabMapper = crontabMapper;
    }

    public Response execute(CrontabQuery query) {
        Page<CrontabDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        crontabMapper.selectPage(page, query);
        List<CrontabVO> crontabVOList = page.getRecords().stream()
                .map(CrontabAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(crontabVOList, (int) page.getTotal());
    }
}
