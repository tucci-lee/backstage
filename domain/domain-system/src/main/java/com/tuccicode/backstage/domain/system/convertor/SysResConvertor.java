package com.tuccicode.backstage.domain.system.convertor;

import com.tuccicode.backstage.domain.system.dataobject.SysResDO;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysResConvertor {

    public static SysRes toEntity(SysResDO db) {
        if (db == null) {
            return null;
        }
        SysRes entity = new SysRes();
        BeanUtils.copyProperties(db, entity);
        return entity;
    }

    public static SysResDO toCreate(SysRes entity) {
        return new SysResDO()
                .setName(entity.getName())
                .setType(entity.getType())
                .setUrl(entity.getUrl())
                .setPid(entity.getPid())
                .setResChar(entity.getResChar())
                .setSeq(entity.getSeq())
                .setCreateTime(System.currentTimeMillis());
    }

    public static SysResDO toUpdate(SysRes entity) {
        return new SysResDO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setUrl(entity.getUrl())
                .setPid(entity.getPid() == null ? 0L : entity.getPid())
                .setResChar(entity.getResChar())
                .setSeq(entity.getSeq())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysResDO toDelete(Long id) {
        return new SysResDO()
                .setId(id)
                .setIsDeleted(Boolean.TRUE)
                .setUpdateTime(System.currentTimeMillis());
    }
}
