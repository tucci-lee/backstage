package com.tuccicode.backstage.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuccicode.backstage.domain.system.dataobject.SysRoleResDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tucci.lee
 */
@Mapper
public interface SysRoleResMapper extends BaseMapper<SysRoleResDO> {

    int countByResId(Long resId);

    int insertList(@Param("roleId") Long roleId, @Param("resIds") List<Long> resIds);

    int deleteByRoleId(Long roleId);
}
