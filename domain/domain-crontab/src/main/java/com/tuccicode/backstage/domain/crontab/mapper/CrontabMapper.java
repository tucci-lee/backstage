package com.tuccicode.backstage.domain.crontab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuccicode.backstage.domain.crontab.dataobject.CrontabDO;
import com.tuccicode.backstage.domain.crontab.entity.CrontabQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tucci.lee
 */
@Mapper
public interface CrontabMapper extends BaseMapper<CrontabDO> {

    Page<CrontabDO> selectPage(Page<CrontabDO> page, @Param("q") CrontabQuery query);
}
