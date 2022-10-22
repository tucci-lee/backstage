package com.tuccicode.backstage.domain.system.service;

import com.tuccicode.backstage.domain.system.entity.SysUser;

/**
 * @author tucci.lee
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser getByUsername(String username);

    /**
     * 根据uid查询用户信息
     *
     * @param uid uid
     * @return SysUser
     */
    SysUser getByUid(Long uid);

    /**
     * 添加用户
     *
     * @param entity 用户信息
     */
    void create(SysUser entity);

    /**
     * 修改用户
     *
     * @param entity 用户信息
     */
    void update(SysUser entity);

    /**
     * 修改密码
     *
     * @param entity 密码信息
     */
    void updatePassword(SysUser entity);

    /**
     * 修改锁定状态
     *
     * @param entity 锁定信息
     */
    void updateLock(SysUser entity);

    /**
     * 删除用户
     *
     * @param uid uid
     */
    void delete(Long uid);

    /**
     * 修改用户关联的角色
     *
     * @param entity 角色信息
     */
    void updateRole(SysUser entity);

}
