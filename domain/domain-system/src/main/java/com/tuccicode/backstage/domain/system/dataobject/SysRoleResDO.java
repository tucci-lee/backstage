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
@TableName("sys_role_res")
public class SysRoleResDO {

    @TableId
    private Long id;
    private Long roleId;
    private Long resId;

}
