package com.tuccicode.backstage.app.system.assembler;

import com.tuccicode.backstage.app.system.dto.vo.SysResVO;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysResAssembler {

    public static SysResVO toVO(SysRes entity) {
        if(entity == null){
            return null;
        }
        SysResVO vo = new SysResVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
