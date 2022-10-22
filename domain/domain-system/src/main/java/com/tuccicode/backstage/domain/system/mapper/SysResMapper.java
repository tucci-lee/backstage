package com.tuccicode.backstage.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuccicode.backstage.domain.system.dataobject.SysResDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tucci.lee
 */
@Mapper
public interface SysResMapper extends BaseMapper<SysResDO> {

    List<SysResDO> selectByUid(Long uid);

    List<SysResDO> selectByRoleId(Long roleId);
}