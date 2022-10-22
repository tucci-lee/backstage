package com.tuccicode.backstage.app.system.assembler;

import com.tuccicode.backstage.app.system.dto.vo.SysDeptVO;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysDeptAssembler {

    public static SysDeptVO toVO(SysDept entity) {
        if(entity == null){
            return null;
        }
        SysDeptVO vo = new SysDeptVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
