package com.tuccicode.backstage.domain.crontab.entity;

import com.tuccicode.concise.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tucci.lee
 */
@Data
public class CrontabLogQuery extends PageQuery {

    @NotNull
    private Long crontabId;
}
