package com.tuccicode.backstage.app.system.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysRoleVO {
    private Long id;
    private String roleChar;
    private String name;
    private String remarks;
    private Long createTime;
    private Long updateTime;
}
