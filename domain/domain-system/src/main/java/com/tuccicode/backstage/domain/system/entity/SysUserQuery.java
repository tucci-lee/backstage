package com.tuccicode.backstage.domain.system.entity;
import com.tuccicode.concise.dto.PageQuery;
import lombok.Data;
/**
 * @author tucci.lee
 */
@Data
public class SysUserQuery extends PageQuery {

    private String username;
    private String phone;
    private Boolean isLock;
    private Long deptId;

}
