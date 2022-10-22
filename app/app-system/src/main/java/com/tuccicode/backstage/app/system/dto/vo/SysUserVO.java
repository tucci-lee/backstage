package com.tuccicode.backstage.app.system.dto.vo;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
public class SysUserVO {
    private Long uid;
    private String username;
    private String phone;
    private String email;
    private Boolean isLock;
    private String nickname;
    private String remarks;
    private Long deptId;
    private Long createTime;
    private Long updateTime;
    private String deptName;
}
