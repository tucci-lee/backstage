package com.tuccicode.backstage.app.system.assembler;

import com.tuccicode.backstage.app.system.dto.vo.SysOperateLogVO;
import com.tuccicode.backstage.domain.system.dataobject.SysOperateLogDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class LogOperateAssembler {

    public static SysOperateLogVO toVO(SysOperateLogDO db) {
        if(db == null){
            return null;
        }
        SysOperateLogVO vo = new SysOperateLogVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
