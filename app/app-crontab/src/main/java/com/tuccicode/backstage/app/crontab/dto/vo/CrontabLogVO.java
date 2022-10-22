package com.tuccicode.backstage.app.crontab.dto.vo;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class CrontabLogVO {
    private Long id;
    private Long crontabId;
    private Boolean status;
    private String message;
    private Long startTime;
    private Long runTime;
    private Long createTime;
}
