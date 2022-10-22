package com.tuccicode.backstage.app.system.exe;

import com.tuccicode.backstage.app.shiro.Subject;
import com.tuccicode.backstage.app.system.assembler.SysResAssembler;
import com.tuccicode.backstage.app.system.dto.vo.SysResVO;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import com.tuccicode.backstage.domain.system.entity.SysUser;
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
public class SysResOwnedMenusExe {

    private final SysResService sysResService;

    public SysResOwnedMenusExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute() {
        Long uid = Subject.getUid();
        List<SysRes> entityList;
        // 如果是admin用户则返回所有菜单
        if (SysUser.isAdmin(uid)) {
            entityList = sysResService.list()
                    .stream()
                    .filter(sysResDO -> sysResDO.getType().equals(1))
                    .collect(Collectors.toList());
        } else {
            entityList = sysResService.listByUid(uid)
                    .stream()
                    .filter(sysRes -> sysRes.getType().equals(1))
                    .collect(Collectors.toList());
        }
        List<SysResVO> voList = entityList.stream()
                .map(SysResAssembler::toVO)
                .collect(Collectors.toList());

        List<SysResVO> tree = SysResVO.tree(voList, SysRes.TOP_ID);
        return SingletonResponse.success(tree);
    }


}
