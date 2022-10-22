package com.tuccicode.backstage.domain.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.constant.CacheConst;
import com.tuccicode.backstage.domain.system.convertor.SysRoleConvertor;
import com.tuccicode.backstage.domain.system.dataobject.SysRoleDO;
import com.tuccicode.backstage.domain.system.entity.SysRole;
import com.tuccicode.backstage.domain.system.mapper.SysRoleMapper;
import com.tuccicode.backstage.domain.system.mapper.SysRoleResMapper;
import com.tuccicode.backstage.domain.system.mapper.SysUserRoleMapper;
import com.tuccicode.backstage.domain.system.service.SysRoleService;
import com.tuccicode.concise.exception.Assert;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;
    private final SysRoleResMapper sysRoleResMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper,
                              SysRoleResMapper sysRoleResMapper,
                              SysUserRoleMapper sysUserRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleResMapper = sysRoleResMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void create(SysRole entity) {
        // 校验角色名称是否有相同的
        Assert.isNull(this.getByName(entity.getName()), BackstageBizCode.ROLE_NAME_EXIST);

        SysRoleDO create = SysRoleConvertor.toCreate(entity);
        sysRoleMapper.insert(create);
        // 添加关联的资源
        List<Long> resIds = entity.getResIds();
        sysRoleResMapper.insertList(create.getId(), resIds);
    }

    @Override
    public void update(SysRole entity) {
        // 校验角色名称是否有相同的
        SysRoleDO role = this.getByName(entity.getName());
        Assert.isTrue(role == null || role.getId().equals(entity.getId()), BackstageBizCode.ROLE_NAME_EXIST);

        SysRoleDO updateRole = SysRoleConvertor.toUpdate(entity);
        sysRoleMapper.updateById(updateRole);
    }

    @Override
    public void delete(Long id) {
        // 是否有用户关联
        int userCount = sysUserRoleMapper.countByRoleId(id);
        Assert.isTrue(userCount == 0, BackstageBizCode.ROLE_RELATED);

        SysRoleDO update = SysRoleConvertor.toDelete(id);
        sysRoleMapper.updateById(update);
    }

    @CacheEvict(value = CacheConst.USER_RES, allEntries = true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateRes(SysRole entity) {
        // 删除之前绑定的资源
        sysRoleResMapper.deleteByRoleId(entity.getId());
        // 添加新绑定的资源
        sysRoleResMapper.insertList(entity.getId(), entity.getResIds());
    }

    @Override
    public List<SysRole> listByUid(Long uid) {
        List<SysRoleDO> sysRoleDOList = sysRoleMapper.selectByUid(uid);
        return sysRoleDOList.stream()
                .map(SysRoleConvertor::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * 根据名称查询角色
     *
     * @param name 名称
     * @return 角色
     */
    private SysRoleDO getByName(String name) {
        LambdaQueryWrapper<SysRoleDO> wrapper = Wrappers.lambdaQuery(SysRoleDO.class)
                .eq(SysRoleDO::getIsDeleted, Boolean.FALSE)
                .eq(SysRoleDO::getName, name);
        return sysRoleMapper.selectOne(wrapper);
    }
}
