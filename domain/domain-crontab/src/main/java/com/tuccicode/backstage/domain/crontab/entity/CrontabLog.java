package com.tuccicode.backstage.domain.crontab.entity;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class CrontabLog {

    private Long id;
    private Long crontabId;
    private Boolean status;
    private String message;
    private Long startTime;
    private Long runTime;
    private Long createTime;

}
