package com.tuccicode.backstage.domain.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.constant.CacheConst;
import com.tuccicode.backstage.domain.system.convertor.SysUserConvertor;
import com.tuccicode.backstage.domain.system.dataobject.SysUserDO;
import com.tuccicode.backstage.domain.system.entity.SysUser;
import com.tuccicode.backstage.domain.system.mapper.SysUserMapper;
import com.tuccicode.backstage.domain.system.mapper.SysUserRoleMapper;
import com.tuccicode.backstage.domain.system.service.SysUserService;
import com.tuccicode.concise.exception.Assert;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tucci.lee
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper,
                              SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUserDO> wrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .eq(SysUserDO::getIsDeleted, Boolean.FALSE)
                .eq(SysUserDO::getUsername, username);
        SysUserDO sysUserDO = sysUserMapper.selectOne(wrapper);
        return SysUserConvertor.toEntity(sysUserDO);
    }

    @Override
    public SysUser getByUid(Long uid) {
        LambdaQueryWrapper<SysUserDO> wrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .eq(SysUserDO::getIsDeleted, Boolean.FALSE)
                .eq(SysUserDO::getUid, uid);
        SysUserDO sysUserDO = sysUserMapper.selectOne(wrapper);
        return SysUserConvertor.toEntity(sysUserDO);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void create(SysUser entity) {
        // 校验用户名是否有相同的
        SysUser queryUser = this.getByUsername(entity.getUsername());
        Assert.isNull(queryUser, BackstageBizCode.ACCOUNT_EXIST);

        SysUserDO sysUserDO = SysUserConvertor.toCreate(entity);
        sysUserMapper.insert(sysUserDO);

        // 添加关联的角色信息
        List<Long> roleIds = entity.getRoleIds();
        sysUserRoleMapper.insertList(sysUserDO.getUid(), roleIds);
    }

    @Override
    public void update(SysUser entity) {
        SysUserDO sysUserDO = SysUserConvertor.toUpdate(entity);
        sysUserMapper.updateById(sysUserDO);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updatePassword(SysUser entity) {
        SysUser user = this.getByUid(entity.getUid());
        SysUserDO update = SysUserConvertor.toUpdatePassword(entity, user.getVersion());
        sysUserMapper.updateById(update);
    }

    @CacheEvict(value = CacheConst.USER_RES, key = "#p0.uid", allEntries = true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateLock(SysUser entity) {
        SysUser user = this.getByUid(entity.getUid());
        SysUserDO update = SysUserConvertor.toUpdateLock(entity, user.getVersion());
        sysUserMapper.updateById(update);
    }

    @CacheEvict(value = CacheConst.USER_RES, key = "#p0", allEntries = true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(Long uid) {
        SysUser user = this.getByUid(uid);
        SysUserDO update = SysUserConvertor.toDelete(uid, user.getVersion());
        sysUserMapper.updateById(update);
    }

    @CacheEvict(value = CacheConst.USER_RES, key = "#p0.uid", allEntries = true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateRole(SysUser entity) {
        // 删除之前绑定的角色
        sysUserRoleMapper.deleteByUid(entity.getUid());
        // 添加新绑定的角色
        sysUserRoleMapper.insertList(entity.getUid(), entity.getRoleIds());
    }
}
