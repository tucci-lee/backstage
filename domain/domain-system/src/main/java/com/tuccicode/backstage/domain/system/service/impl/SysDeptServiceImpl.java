package com.tuccicode.backstage.domain.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuccicode.backstage.domain.core.exception.BackstageBizCode;
import com.tuccicode.backstage.domain.system.convertor.SysDeptConvertor;
import com.tuccicode.backstage.domain.system.dataobject.SysDeptDO;
import com.tuccicode.backstage.domain.system.dataobject.SysUserDO;
import com.tuccicode.backstage.domain.system.entity.SysDept;
import com.tuccicode.backstage.domain.system.mapper.SysDeptMapper;
import com.tuccicode.backstage.domain.system.mapper.SysUserMapper;
import com.tuccicode.backstage.domain.system.service.SysDeptService;
import com.tuccicode.concise.exception.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci.lee
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;

    public SysDeptServiceImpl(SysDeptMapper sysDeptMapper,
                              SysUserMapper sysUserMapper) {
        this.sysDeptMapper = sysDeptMapper;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<SysDept> list() {
        LambdaQueryWrapper<SysDeptDO> wrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, Boolean.FALSE);
        List<SysDeptDO> sysDeptDOList = sysDeptMapper.selectList(wrapper);
        return sysDeptDOList.stream()
                .map(SysDeptConvertor::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SysDept getById(Long id) {
        LambdaQueryWrapper<SysDeptDO> wrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, Boolean.FALSE)
                .eq(SysDeptDO::getId, id);
        SysDeptDO sysDeptDO = sysDeptMapper.selectOne(wrapper);
        return SysDeptConvertor.toEntity(sysDeptDO);
    }

    @Override
    public void create(SysDept entity) {
        // 校验上级是否存在
        this.verifyParent(entity.getPid());
        // 校验名称是否有相同的
        Assert.isNull(this.getByName(entity.getName()), BackstageBizCode.DEPT_NAME_EXIST);

        SysDeptDO create = SysDeptConvertor.toCreate(entity);
        sysDeptMapper.insert(create);
    }

    @Override
    public void update(SysDept entity) {
        // 校验上级id是否是自己的id
        Assert.isTrue(!entity.getId().equals(entity.getPid()), BackstageBizCode.LEVEL_ERROR);
        // 校验上级是否存在
        this.verifyParent(entity.getPid());
        // 校验名称是否重复
        SysDeptDO dept = this.getByName(entity.getName());
        Assert.isTrue(dept == null || dept.getId().equals(entity.getId()), BackstageBizCode.DEPT_NAME_EXIST);

        SysDeptDO update = SysDeptConvertor.toUpdate(entity);
        sysDeptMapper.updateById(update);
    }

    @Override
    public void delete(Long id) {
        // 判断是否有用户关联
        LambdaQueryWrapper<SysUserDO> countUserWrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .eq(SysUserDO::getIsDeleted, Boolean.FALSE)
                .eq(SysUserDO::getDeptId, id);
        int userCount = sysUserMapper.selectCount(countUserWrapper);
        Assert.isTrue(userCount == 0, BackstageBizCode.DEPT_RELATED);

        // 判断是否有下级
        LambdaQueryWrapper<SysDeptDO> countSubWrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, Boolean.FALSE)
                .eq(SysDeptDO::getPid, id);
        int subCount = sysDeptMapper.selectCount(countSubWrapper);
        Assert.isTrue(subCount == 0, BackstageBizCode.DEPT_HAS_SUB);

        SysDeptDO update = SysDeptConvertor.toDelete(id);
        sysDeptMapper.updateById(update);
    }

    /**
     * 根据名称查询部门
     *
     * @param name 名称
     * @return 部门信息
     */
    private SysDeptDO getByName(String name) {
        LambdaQueryWrapper<SysDeptDO> wrapper = Wrappers.lambdaQuery(SysDeptDO.class)
                .eq(SysDeptDO::getIsDeleted, Boolean.FALSE)
                .eq(SysDeptDO::getName, name);
        return sysDeptMapper.selectOne(wrapper);
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
        SysDept parent = this.getById(pid);
        Assert.notNull(parent, BackstageBizCode.PARENT_NOT_EXIST);
    }
}
