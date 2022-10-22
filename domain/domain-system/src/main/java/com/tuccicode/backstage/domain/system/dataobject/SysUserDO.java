package com.tuccicode.backstage.domain.system.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tucci.lee
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserDO {

    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickname;
    private String remarks;
    private Long deptId;
    private Long version;
    private Boolean isLock;
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateTime;
    private Boolean isDeleted;

}