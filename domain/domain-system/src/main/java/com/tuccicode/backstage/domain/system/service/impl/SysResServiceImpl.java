package com.tuccicode.backstage.domain.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.constant.CacheConst;
import com.tuccicode.backstage.domain.system.convertor.SysResConvertor;
import com.tuccicode.backstage.domain.system.dataobject.SysResDO;
import com.tuccicode.backstage.domain.system.entity.SysRes;
import com.tuccicode.backstage.domain.system.mapper.SysResMapper;
import com.tuccicode.backstage.domain.system.mapper.SysRoleResMapper;
import com.tuccicode.backstage.domain.system.service.SysResService;
import com.tuccicode.concise.exception.Assert;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysResServiceImpl implements SysResService {

    private final SysResMapper sysResMapper;
    private final SysRoleResMapper sysRoleResMapper;

    public SysResServiceImpl(SysResMapper sysResMapper,
                             SysRoleResMapper sysRoleResMapper) {
        this.sysResMapper = sysResMapper;
        this.sysRoleResMapper = sysRoleResMapper;
    }

    @Override
    public List<SysRes> list() {
        LambdaQueryWrapper<SysResDO> wrapper = Wrappers.lambdaQuery(SysResDO.class)
                .eq(SysResDO::getIsDeleted, Boolean.FALSE);
        List<SysResDO> sysResDOList = sysResMapper.selectList(wrapper);
        return sysResDOList.stream()
                .map(SysResConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SysRes getById(Long id) {
        LambdaQueryWrapper<SysResDO> wrapper = Wrappers.lambdaQuery(SysResDO.class)
                .eq(SysResDO::getIsDeleted, Boolean.FALSE)
                .eq(SysResDO::getId, id);
        SysResDO sysResDO = sysResMapper.selectOne(wrapper);
        return SysResConvertor.toEntity(sysResDO);
    }

    @Override
    public void create(SysRes entity) {
        // 校验上级是否存在
        this.verifyParent(entity.getPid());
        // 校验名称是否有相同的
        Assert.isNull(this.getByName(entity.getName()), BackstageBizCode.RES_NAME_EXIST);

        SysResDO create = SysResConvertor.toCreate(entity);
        sysResMapper.insert(create);
    }


    @CacheEvict(value = CacheConst.USER_RES, allEntries = true)
    @Override
    public void update(SysRes entity) {
        // 校验上级id是否是自己的id
        Assert.isTrue(!entity.getId().equals(entity.getPid()), BackstageBizCode.LEVEL_ERROR);
        // 校验上级是否存在
        this.verifyParent(entity.getPid());
        // 校验名称是否重复
        SysResDO res = this.getByName(entity.getName());
        Assert.isTrue(res == null || res.getId().equals(entity.getId()), BackstageBizCode.RES_NAME_EXIST);

        SysResDO update = SysResConvertor.toUpdate(entity);
        sysResMapper.updateById(update);
    }

    @Override
    public void delete(Long id) {
        // 是否有角色关联
        int roleCount = sysRoleResMapper.countByResId(id);
        Assert.isTrue(roleCount == 0, BackstageBizCode.RES_RELATED);

        SysResDO update = SysResConvertor.toDelete(id);
        sysResMapper.updateById(update);
    }

    @Cacheable(value = CacheConst.USER_RES, key = "#p0")
    @Override
    public List<SysRes> listByUid(Long uid) {
        List<SysResDO> sysResDOList = sysResMapper.selectByUid(uid);
        return sysResDOList.stream()
                .map(SysResConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SysRes> listByRoleId(Long roleId) {
        List<SysResDO> sysResDOList = sysResMapper.selectByRoleId(roleId);
        return sysResDOList.stream()
                .map(SysResConvertor::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * 根据名称查询资源
     *
     * @param name 名称
     * @return 资源信息
     */
    private SysResDO getByName(String name) {
        LambdaQueryWrapper<SysResDO> wrapper = Wrappers.lambdaQuery(SysResDO.class)
                .eq(SysResDO::getIsDeleted, Boolean.FALSE)
                .eq(SysResDO::getName, name);
        return sysResMapper.selectOne(wrapper);
    }

    /**
     * 校验上级是否存在
     *
     * @param pid 上级id
     */
    private void verifyParent(Long pid) {
        if (pid == null || pid == 0) {
            return;
        }
        SysRes parent = this.getById(pid);
        Assert.notNull(parent, BackstageBizCode.PARENT_NOT_EXIST);
    }
}
