package com.tuccicode.backstage.domain.system.convertor;

import com.tuccicode.backstage.domain.system.dataobject.SysUserDO;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci.lee
 */
public class SysUserConvertor {

    public static SysUser toEntity(SysUserDO entity) {
        if (entity == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(entity, sysUser);
        return sysUser;
    }

    public static SysUserDO toCreate(SysUser entity) {
        return new SysUserDO()
                .setUsername(entity.getUsername().toLowerCase())
                .setPassword(SysUser.password(entity.getPassword()))
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setNickname(entity.getNickname())
                .setRemarks(entity.getRemarks())
                .setDeptId(entity.getDeptId())
                .setCreateTime(System.currentTimeMillis());
    }

    public static SysUserDO toUpdate(SysUser entity) {
        return new SysUserDO()
                .setUid(entity.getUid())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setNickname(entity.getNickname())
                .setRemarks(entity.getRemarks())
                .setDeptId(entity.getDeptId())
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysUserDO toUpdatePassword(SysUser entity, Long version) {
        return new SysUserDO()
                .setUid(entity.getUid())
                .setPassword(SysUser.password(entity.getPassword()))
                .setVersion(version + 1)
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysUserDO toUpdateLock(SysUser entity, Long version) {
        return new SysUserDO()
                .setUid(entity.getUid())
                .setIsLock(entity.getIsLock())
                .setVersion(version + 1)
                .setUpdateTime(System.currentTimeMillis());
    }

    public static SysUserDO toDelete(Long uid, Long version) {
        return new SysUserDO()
                .setUid(uid)
                .setIsDeleted(Boolean.TRUE)
                .setVersion(version + 1)
                .setUpdateTime(System.currentTimeMillis());
    }
}
