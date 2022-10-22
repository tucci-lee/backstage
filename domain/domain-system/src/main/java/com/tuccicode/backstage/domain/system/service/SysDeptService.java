package com.tuccicode.backstage.domain.system.service;

import com.tuccicode.backstage.domain.system.entity.SysDept;

import java.util.List;

/**
 * @author tucci.lee
 */
public interface SysDeptService {

    /**
     * 查询所有的部门列表
     *
     * @return List<SysDept>
     */
    List<SysDept> list();

    /**
     * 根据id查询部门
     *
     * @param id id
     * @return 部门信息
     */
    SysDept getById(Long id);

    /**
     * 保存部门
     *
     * @param entity 部门信息
     */
    void create(SysDept entity);

    /**
     * 修改部门
     *
     * @param entity 部门信息
     */
    void update(SysDept entity);


    /**
     * 根据id删除部门
     *
     * @param id id
     */
    void delete(Long id);
}
