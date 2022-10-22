package com.tuccicode.backstage.domain.system.convertor;

import com.tuccicode.backstage.domain.system.dataobject.SysDeptDO;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysDeptConvertor {

    public static SysDept toEntity(SysDeptDO db) {
        if (db == null) {
            return null;
        }
        SysDept entity = new SysDept();
        BeanUtils.copyProperties(db, entity);
        return entity;
    }

    public static SysDeptDO toCreate(SysDept entity) {
        return new SysDeptDO()
                .setName(entity.getName())
                .setPid(entity.getPid())
                .setSeq(entity.getSeq())
                .setManager(entity.getManager())
                .setManagerPhone(entity.getManagerPhone())
                .setCreateTime(System.currentTimeMillis());
    }

    public static SysDeptDO toUpdate(SysDept entity) {
        return new SysDeptDO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setId(entity.getPid() == null ? 0L : entity.getPid())
                .setSeq(entity.getSeq())
                .setManager(entity.getManager())
                .setManagerPhone(entity.getManagerPhone())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysDeptDO toDelete(Long id) {
        return new SysDeptDO()
                .setId(id)
                .setIsDeleted(Boolean.TRUE)
                .setUpdateTime(System.currentTimeMillis());
    }
}
