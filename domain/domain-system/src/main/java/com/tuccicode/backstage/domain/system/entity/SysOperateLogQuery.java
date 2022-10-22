package com.tuccicode.backstage.domain.system.entity;
import com.tuccicode.concise.dto.PageQuery;
import lombok.Data;
/**
 * @author tucci.lee
 */
@Data
public class SysOperateLogQuery extends PageQuery {

    private String username;
    private String ip;
    private Boolean status;
    private Long beginTime;
    private Long endTime;

}
