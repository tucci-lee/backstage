package com.tuccicode.backstage.app.system.assembler;

import com.tuccicode.backstage.app.system.dto.vo.SysLoginLogVO;
import com.tuccicode.backstage.domain.system.dataobject.SysLoginLogDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class LogLoginAssembler {

    public static SysLoginLogVO toVO(SysLoginLogDO db) {
        if(db == null){
            return null;
        }
        SysLoginLogVO vo = new SysLoginLogVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
