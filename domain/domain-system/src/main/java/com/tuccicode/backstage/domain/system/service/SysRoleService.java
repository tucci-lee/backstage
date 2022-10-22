package com.tuccicode.backstage.domain.system.service;

import com.tuccicode.backstage.domain.system.entity.SysRole;

import java.util.List;

/**
 * @author tucci.lee
 */
public interface SysRoleService {

    /**
     * 添加角色
     *
     * @param entity 角色信息
     */
    void create(SysRole entity);

    /**
     * 修改角色
     *
     * @param entity 角色信息
     */
    void update(SysRole entity);

    /**
     * 根绝id删除角色
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 修改角色关联的资源
     *
     * @param entity 角色信息
     */
    void updateRes(SysRole entity);

    /**
     * 根据uid查询关联的角色
     *
     * @param uid uid
     * @return 角色列表
     */
    List<SysRole> listByUid(Long uid);
}
