package com.tuccicode.backstage.domain.crontab.entity;

import com.tuccicode.concise.dto.PageQuery;
import lombok.Data;

/**
 * @author tucci.lee
 */
@Data
public class CrontabQuery extends PageQuery {

    private String name;
    private Boolean status;

}
