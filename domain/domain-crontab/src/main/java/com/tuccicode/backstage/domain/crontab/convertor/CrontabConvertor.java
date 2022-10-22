package com.tuccicode.backstage.domain.crontab.convertor;

import com.tuccicode.backstage.domain.crontab.dataobject.CrontabDO;
import com.tuccicode.backstage.domain.crontab.entity.Crontab;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class CrontabConvertor {

    public static Crontab toEntity(CrontabDO db) {
        if (db == null) {
            return null;
        }
        Crontab entity = new Crontab();
        BeanUtils.copyProperties(db, entity);
        return entity;
    }

    public static CrontabDO toCreate(Crontab entity) {
        return new CrontabDO()
                .setName(entity.getName())
                .setClassName(entity.getClassName())
                .setCron(entity.getCron())
                .setRemarks(entity.getRemarks())
                .setCreateTime(System.currentTimeMillis());
    }

    public static CrontabDO toUpdate(Crontab entity) {
        return new CrontabDO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setClassName(entity.getClassName())
                .setCron(entity.getCron())
                .setRemarks(entity.getRemarks())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static CrontabDO toUpdateStatus(Crontab entity) {
        return new CrontabDO()
                .setId(entity.getId())
                .setStatus(entity.getStatus())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static CrontabDO toDelete(Long id) {
        return new CrontabDO()
                .setId(id)
                .setIsDeleted(Boolean.TRUE)
                .setUpdateTime(System.currentTimeMillis());
    }
}
