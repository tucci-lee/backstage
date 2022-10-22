package com.tuccicode.backstage.domain.system.entity;

import com.tuccicode.concise.dto.PageQuery;
import lombok.Data;

/**
 * @author tucci.lee
 */
@Data
public class SysRoleQuery extends PageQuery {

    private String name;

}

