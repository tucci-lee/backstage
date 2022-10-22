package com.tuccicode.backstage.app.crontab.assembler;

import com.tuccicode.backstage.app.crontab.dto.vo.CrontabLogVO;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabLogDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class CrontabLogAssembler {

    public static CrontabLogVO toVO(CrontabLogDO db) {
        if(db == null){
            return null;
        }
        CrontabLogVO vo = new CrontabLogVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
