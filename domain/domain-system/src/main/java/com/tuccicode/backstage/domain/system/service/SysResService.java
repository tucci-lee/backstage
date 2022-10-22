package com.tuccicode.backstage.domain.system.service;

import com.tuccicode.backstage.domain.system.entity.SysRes;

import java.util.List;

/**
 * @author tucci.lee
 */
public interface SysResService {

    /**
     * 查询所有的资源列表
     *
     * @return 资源列表
     */
    List<SysRes> list();

    /**
     * 根据id查询资源信息
     *
     * @param id id
     * @return 资源信息
     */
    SysRes getById(Long id);

    /**
     * 保存资源
     *
     * @param entity 资源信息
     */
    void create(SysRes entity);

    /**
     * 修改资源
     *
     * @param entity 资源信息
     */
    void update(SysRes entity);

    /**
     * 根据id删除资源
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 根据uid查询拥有的资源列表
     *
     * @param uid uid
     * @return 资源列表
     */
    List<SysRes> listByUid(Long uid);

    /**
     * 根据角色id查询关联的资源
     *
     * @param roleId 角色id
     * @return 资源列表
     */
    List<SysRes> listByRoleId(Long roleId);
}
