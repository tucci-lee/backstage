package com.tuccicode.backstage.app.crontab.assembler;

import com.tuccicode.backstage.app.crontab.dto.vo.CrontabVO;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class CrontabAssembler {

    public static CrontabVO toVO(CrontabDO db) {
        if(db == null){
            return null;
        }
        CrontabVO vo = new CrontabVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
