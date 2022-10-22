package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.system.assembler.SysDeptAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysDeptVO;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import com.tuccicode.backstage.domain.system.service.SysDeptService;
import com.tuccicode.concise.dto.Response;
import com.tuccicode.concise.dto.SingletonResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysDeptTreeExe {

    private final SysDeptService sysDeptService;

    public SysDeptTreeExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute() {
        List<SysDept> entityList = sysDeptService.list();
        List<SysDeptVO> voList = entityList.stream()
                .map(SysDeptAssembler::toVO)
                .collect(Collectors.toList());

        List<SysDeptVO> tree = SysDeptVO.tree(voList, SysDept.TOP_ID);
        return SingletonResponse.success(tree);
    }


}
