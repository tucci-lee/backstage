package com.tuccicode.backstage.domain.system.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
public class SysUserRoleDO {

    @TableId
    private Long id;
    private Long uid;
    private Long roleId;

}
