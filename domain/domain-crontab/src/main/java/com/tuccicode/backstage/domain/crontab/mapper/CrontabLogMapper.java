package com.tuccicode.backstage.domain.crontab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabLogDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tucci.lee
 */
@Mapper
public interface CrontabLogMapper extends BaseMapper<CrontabLogDO> {

    Page<CrontabLogDO> selectPage(Page<CrontabLogDO> page, @Param("q") CrontabLogQuery query);
}
