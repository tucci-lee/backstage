package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.assembler.SysResAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysResVO;
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
public class SysResTreeExe {

    private final SysResService sysResService;

    public SysResTreeExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute() {
        List<SysRes> entityList = sysResService.list();
        List<SysResVO> voList = entityList.stream()
                .map(SysResAssembler::toVO)
                .collect(Collectors.toList());

        List<SysResVO> tree = SysResVO.tree(voList, SysRes.TOP_ID);
        return SingletonResponse.success(tree);
    }
}
