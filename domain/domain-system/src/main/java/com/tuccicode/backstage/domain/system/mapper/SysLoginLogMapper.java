package com.tuccicode.backstage.domain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.domain.system.dataobject.SysLoginLogDO;
import com.tuccicode.backstage.domain.system.entity.SysLoginLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tucci.lee
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLogDO> {

    Page<SysLoginLogDO> selectPage(Page<SysLoginLogDO> page, @Param("q") SysLoginLogQuery query);
}
