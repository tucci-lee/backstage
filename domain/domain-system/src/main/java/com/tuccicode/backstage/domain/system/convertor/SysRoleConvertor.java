package com.tuccicode.backstage.domain.system.convertor;

import com.tuccicode.backstage.domain.system.dataobject.SysRoleDO;
import com.tuccicode.backstage.domain.system.entity.SysRole;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysRoleConvertor {

    public static SysRole toEntity(SysRoleDO db) {
        if (db == null) {
            return null;
        }
        SysRole entity = new SysRole();
        BeanUtils.copyProperties(db, entity);
        return entity;
    }

    public static SysRoleDO toCreate(SysRole entity) {
        return new SysRoleDO()
                .setName(entity.getName())
                .setRoleChar(entity.getRoleChar())
                .setRemarks(entity.getRemarks())
                .setCreateTime(System.currentTimeMillis());
    }

    public static SysRoleDO toUpdate(SysRole entity) {
        return new SysRoleDO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setRoleChar(entity.getRoleChar())
                .setRemarks(entity.getRemarks())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysRoleDO toDelete(Long id) {
        return new SysRoleDO()
                .setId(id)
                .setIsDeleted(Boolean.TRUE)
                .setUpdateTime(System.currentTimeMillis());
    }
}
