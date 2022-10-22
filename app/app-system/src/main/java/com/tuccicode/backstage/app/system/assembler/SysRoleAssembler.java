package com.tuccicode.backstage.app.system.assembler;

import com.tuccicode.backstage.app.system.dto.vo.SysRoleVO;
import com.tuccicode.backstage.domain.system.dataobject.SysRoleDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysRoleAssembler {

    public static SysRoleVO toVO(SysRoleDO db) {
        if(db == null){
            return null;
        }
        SysRoleVO vo = new SysRoleVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
