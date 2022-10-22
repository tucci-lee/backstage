package com.tuccicode.backstage.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.domain.system.dataobject.SysUserDO;
import com.tuccicode.backstage.domain.system.entity.SysUserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tucci.lee
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {

    Page<SysUserDO> selectPage(Page<?> page, @Param("q") SysUserQuery query);

}
