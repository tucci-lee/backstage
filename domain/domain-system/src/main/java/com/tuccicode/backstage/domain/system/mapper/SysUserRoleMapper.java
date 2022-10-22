package com.tuccicode.backstage.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuccicode.backstage.domain.system.dataobject.SysUserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tucci.lee
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleDO> {

    int countByRoleId(Long roleId);

    int insertList(@Param("uid") Long uid, @Param("roleIds") List<Long> roleIds);

    void deleteByUid(Long uid);
}
