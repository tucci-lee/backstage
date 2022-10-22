package com.tuccicode.backstage.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.domain.system.dataobject.SysRoleDO;
import com.tuccicode.backstage.domain.system.entity.SysRoleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tucci.lee
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {

    Page<SysRoleDO> selectPage(Page<?> page, @Param("q") SysRoleQuery query);

    List<SysRoleDO> selectByUid(Long uid);
}
