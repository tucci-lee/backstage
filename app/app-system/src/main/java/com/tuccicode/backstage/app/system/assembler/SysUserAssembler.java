package com.tuccicode.backstage.app.system.assembler;

import com.tuccicode.backstage.app.system.dto.vo.SysUserVO;
import com.tuccicode.backstage.domain.system.dataobject.SysUserDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysUserAssembler {

    public static SysUserVO toVO(SysUserDO db, String deptName){
        if(db == null){
            return null;
        }
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(db, vo);
        vo.setDeptName(deptName);
        return vo;
    }
}
